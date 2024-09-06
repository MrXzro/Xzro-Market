package com.xzro.service.impl;

import com.xzro.bean.Order;
import com.xzro.mapper.OrdersMapper;
import com.xzro.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: OrdersServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/5 20:40
 * @Version 1.0
 */
@Service
public class OrdersServiceImpl implements OrdersService {
    @Autowired
    private OrdersMapper ordersMapper;
    //查询所有订单
    @Override
    public List<Order> selectAll() {
        return ordersMapper.selectAll("");
    }
    //根据ID查询订单
    @Override
    public Order selectById(Integer id) {
        return ordersMapper.selectById(id);
    }
    //删除订单
    @Override
    public boolean delete(Integer id) {
        return ordersMapper.delete(id)!=0;
    }
    //更新订单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Order order, Integer[] goods) {
        ordersMapper.update(order);
        ordersMapper.deleteAssociate(order.getId());
        if (goods.length != 0) {
            ordersMapper.insertAssociate(order.getId(), goods);
        }
        return true;
    }
    //添加订单
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean insert(Order order, Integer[] goods) {
        ordersMapper.insert(order);
        if (goods.length != 0) {
            ordersMapper.insertAssociate(order.getId(), goods);
        }
        return true;
    }
}
