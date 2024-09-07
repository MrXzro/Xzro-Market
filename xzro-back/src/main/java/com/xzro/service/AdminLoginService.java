package com.xzro.service;

import com.xzro.bean.Admin;

/**
 * ClassName: AdminLoginService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/7 18:05
 * @Version 1.0
 */
public interface AdminLoginService {
    Admin adminLogin(String username);
}
