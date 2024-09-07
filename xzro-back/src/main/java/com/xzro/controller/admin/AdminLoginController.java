package com.xzro.controller.admin;

import cn.hutool.crypto.SecureUtil;
import com.xzro.bean.Admin;
import com.xzro.bean.RespBean;
import com.xzro.service.AdminLoginService;
import com.xzro.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

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
    @PostMapping("/login")
    public RespBean adminLogin(@RequestBody Map<String,Object> userDate){
        String username = (String) userDate.get("username");
        String password = SecureUtil.md5(SecureUtil.md5((String) userDate.get("password")));
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
