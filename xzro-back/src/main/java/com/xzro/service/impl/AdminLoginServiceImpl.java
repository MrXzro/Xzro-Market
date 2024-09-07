package com.xzro.service.impl;

import com.xzro.bean.Admin;
import com.xzro.mapper.AdminLoginMapper;
import com.xzro.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: AdminLoginServiceImpl
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/7 18:06
 * @Version 1.0
 */
@Service
public class AdminLoginServiceImpl implements AdminLoginService {
    @Autowired
    private AdminLoginMapper adminLoginMapper;
    @Override
    public Admin adminLogin(String username) {
        return adminLoginMapper.selectByUserNameAndPassword(username);
    }
}
