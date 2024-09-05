package com.xzro.service.impl;

import com.xzro.bean.Customer;
import com.xzro.mapper.CustomerMapper;
import com.xzro.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CustomerServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/5 11:05
 * @Version 1.0
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public List<Customer> selectAll() {
        return customerMapper.selectAll("");
    }

    @Override
    public Customer selectById(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public int delete(Integer id) {
        return customerMapper.deleteById(id);
    }

    @Override
    public int insert(Customer customer) {
        return customerMapper.insert(customer);
    }

    @Override
    public int update(Customer customer) {
        return customerMapper.update(customer);
    }
}
