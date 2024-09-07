package com.xzro.service.impl;

import com.xzro.bean.Customer;
import com.xzro.mapper.CustomerLoginMapper;
import com.xzro.mapper.CustomerMapper;
import com.xzro.service.CustomerLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * ClassName: CustomerLoginServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/7 18:06
 * @Version 1.0
 */
@Service
public class CustomerLoginServiceImpl implements CustomerLoginService {
    @Autowired
    private CustomerLoginMapper customerLoginMapper;
    @Override
    public Customer customerLogin(String username) {
        return customerLoginMapper.selectByUserNameAndPassword(username);
    }
}
