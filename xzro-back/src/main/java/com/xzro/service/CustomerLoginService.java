package com.xzro.service;

import com.xzro.bean.Customer;

/**
 * ClassName: CustomerLoginService
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/7 18:05
 * @Version 1.0
 */
public interface CustomerLoginService {
    Customer customerLogin(String username);
}
