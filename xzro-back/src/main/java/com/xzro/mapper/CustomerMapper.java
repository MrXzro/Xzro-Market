package com.xzro.mapper;

import com.xzro.bean.Customer;

import java.util.List;

/**
 * ClassName: CustomerService
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 11:48
 * @Version 1.0
 */
public interface CustomerMapper {
    //TODO:根据分组查询用户
    //TODO:根据 手机号码/ 查询所有用户

    //查询所有用户(根据姓名查询)
    List<Customer> selectAll(String name);
    //根据ID查询
    Customer selectById(Integer id);
    //增加用户
    int insert(Customer customer);
    //根据ID删除用户
    int deleteById(Integer id);
    //修改用户
    int update(Customer customer);

}
