package com.xzro.controller.user;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.core.util.IdUtil;
import cn.hutool.crypto.SecureUtil;
import com.xzro.bean.Customer;
import com.xzro.bean.RespBean;
import com.xzro.service.CustomerLoginService;
import com.xzro.utils.JwtUtils;
import com.xzro.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: CustomerLoginController
 * Package: com.xzro.controller.user
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/7 18:11
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/customer")
public class CustomerLoginController {
    @Autowired
    private CustomerLoginService customerLoginService;
    @Autowired
    private RedisUtil redisUtil;
    @PostMapping("/login")
    public RespBean customerLogin(@RequestBody Map<String, Object> userDate) {
        String username = (String) userDate.get("username");
        String password = SecureUtil.md5(SecureUtil.md5((String) userDate.get("password")));
        String captchaId = (String) userDate.get("captchaId");
        String captcha = (String) userDate.get("captcha");
        //获取正确的验证码
        String captchaCode = (String)redisUtil.get(captchaId);
        //比较验证码
        System.out.println(captcha);
        System.out.println(captchaCode);
        if (captcha==null||(captcha!=null&&!captcha.equalsIgnoreCase(captchaCode))) {
            return RespBean.error("验证码错误");
        }
        Customer customer = customerLoginService.customerLogin(username);
        if (customer != null) {
            if (customer.getPassword().equals(password)) {
                Integer id = customer.getId();
                Map<String, Object> map = new HashMap<>();
                map.put("username", username);
                map.put("id", id);
                String token = JwtUtils.generateJwt(map);
                return RespBean.ok("登录成功", token);
            }
        }
        return RespBean.error("账号或密码错误");
    }
    @PostMapping("/logout")
    public RespBean customerLogout(HttpServletRequest request){
        String token = request.getHeader("token");
        if (token != null) {
            JwtUtils.invalidateToken(token);
            return RespBean.ok("退出成功");
        }
        return RespBean.error("退出失败");
    }
    @GetMapping("/captcha")
    public RespBean captcha(){
        LineCaptcha lineCaptcha = new LineCaptcha(120, 38, 4, 20);
        //生成唯一的ID
        String captchaId = IdUtil.getSnowflakeNextIdStr();
        //获取验证码的文本
        String code = lineCaptcha.getCode();
        //获取验证码的文本保存到Redis中
        redisUtil.set(captchaId, code, 60);
        //将CapatchaId和验证码图片发送给浏览器
        String imageBase64Data = lineCaptcha.getImageBase64Data();
        HashMap<String, Object> map = new HashMap<>();
        map.put("captchaId", captchaId);
        map.put("imageBase64Data", imageBase64Data);
        return RespBean.ok("获取成功", map);
    }
}
