package com.xzro.controller.user;

import cn.hutool.crypto.SecureUtil;
import com.xzro.bean.Customer;
import com.xzro.bean.RespBean;
import com.xzro.service.CustomerLoginService;
import com.xzro.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

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
    @PostMapping("/login")
    public RespBean customerLogin(@RequestBody Map<String, Object> userDate) {
        String username = (String) userDate.get("username");
        String password = SecureUtil.md5(SecureUtil.md5((String) userDate.get("password")));
        System.out.println(username);
        Customer customer = customerLoginService.customerLogin(username);
        if (customer != null) {
            if (customer.getPassword().equals(password)) {
                Integer id = customer.getId();
                Map<String, Object> map = new HashMap<>();
                map.put("usernasme", username);
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
}
