package com.xzro.mapper;

import com.xzro.bean.Customer;

/**
 * ClassName: CustomerLoginMapper
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/7 18:00
 * @Version 1.0
 */
public interface CustomerLoginMapper {
    //根据用户名和密码的查询
    Customer selectByUserNameAndPassword(String username);
}
