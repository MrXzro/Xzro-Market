package com.xzro.controller.admin;

import cn.hutool.captcha.LineCaptcha;
import cn.hutool.crypto.SecureUtil;
import com.xzro.bean.Admin;
import com.xzro.bean.RespBean;
import com.xzro.service.AdminLoginService;
import com.xzro.utils.JwtUtils;
import com.xzro.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sound.sampled.Line;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * ClassName: AdminLoginController
 * Package: com.xzro.controller.user
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/7 18:10
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;
    @Autowired
    private RedisUtil redisUtil;

    @PostMapping("/login")
    public RespBean adminLogin(@RequestBody Map<String,Object> userDate){

        String username = (String) userDate.get("username");
        String password = SecureUtil.md5(SecureUtil.md5((String) userDate.get("password")));
        String captchaId = (String) userDate.get("captchaId");
        String captcha = (String) userDate.get("captcha");
        //获取正确的验证码
        String captchaCode = (String)redisUtil.get(captchaId);
        //比较验证码
        if (captcha==null||(captcha!=null&&!captcha.equalsIgnoreCase(captchaCode))) {
            return RespBean.error("验证码错误");
        }
        if (username.equals("")) {
            return RespBean.error("用户名不能为空");
        }
        if (password.equals("")) {
            return RespBean.error("密码不能为空");
        }
        System.out.println(username);
        Admin admin = adminLoginService.adminLogin(username);
        if (admin!=null){
            if (admin.getPassword().equals(password)) {
                Map<String, Object> map = new HashMap<>();
                map.put("usernasme", username);
                map.put("isAdmin", "admin");
                String token = JwtUtils.generateJwt(map);
                return RespBean.ok("登录成功",token);
            }
        }
        return RespBean.error("账号或密码错误");
    }
    @PostMapping("/logout")
    public RespBean adminLogout(HttpServletRequest request){
        String token = request.getHeader("token");
        JwtUtils.invalidateToken(token);
        return RespBean.ok("退出成功");
    }

}
