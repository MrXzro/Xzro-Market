package com.xzro.service;

import com.xzro.bean.Order;

import java.util.List;

/**
 * ClassName: OrdersService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/5 20:20
 * @Version 1.0
 */
public interface OrdersService {
    //查询所有订单
    List<Order> selectAll(String name, Integer id);

    //根据ID查询订单
    Order selectById(Integer id);

    //删除订单
    boolean delete(Integer id);

    //更新订单
    boolean update(Order order, Integer[] goods);

    //添加订单
    boolean insert(Order order, Integer[] goods);

}
