package com.xzro.mapper;

import com.xzro.bean.Admin;

/**
 * ClassName: AdminLoginMapper
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/7 18:01
 * @Version 1.0
 */
public interface AdminLoginMapper {
    //根据用户名和密码的查询
    Admin selectByUserNameAndPassword(String username);
}
