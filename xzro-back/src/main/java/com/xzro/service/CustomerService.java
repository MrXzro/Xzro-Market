package com.xzro.service;

import com.xzro.bean.Customer;

import java.util.List;

/**
 * ClassName: CustomerService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/5 11:01
 * @Version 1.0
 */
public interface CustomerService {
    //TODO:适配根据姓名查询
    //查询全部
    List<Customer> selectAll();

    //跟据ID查询
    Customer selectById(Integer id);

    //根据分组查询用户
    List<Customer> selectByGroup(Integer id);
    //删除客户
    boolean delete(Integer id);

    //新增客户
    boolean insert(Customer customer);

    //修改客户
    boolean update(Customer customer);


}
