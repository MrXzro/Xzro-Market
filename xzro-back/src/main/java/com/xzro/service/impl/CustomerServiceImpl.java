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
    public List<Customer> selectAll(String name) {
        return customerMapper.selectAll(name);
    }

    @Override
    public Customer selectById(Integer id) {
        return customerMapper.selectById(id);
    }

    @Override
    public List<Customer> selectByGroup(Integer id) {
        return customerMapper.selectByGroup(id);
    }

    @Override
    public boolean delete(Integer id) {
        return customerMapper.deleteById(id)!=0;
    }

    @Override
    public boolean insert(Customer customer) {
        return customerMapper.insert(customer)!=0;
    }

    @Override
    public boolean update(Customer customer) {
        return customerMapper.update(customer)!=0;
    }
}
