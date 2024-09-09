package com.xzro.service.impl;

import com.xzro.bean.Ggroup;
import com.xzro.bean.Good;
import com.xzro.bean.Order;
import com.xzro.exception.XzroException;
import com.xzro.mapper.OrdersMapper;
import com.xzro.service.GoodsService;
import com.xzro.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private GoodsService goodsService;

    //查询所有订单
    @Override
    public List<Order> selectAll(String name, Integer id) {
        return ordersMapper.selectAll(name, id);
    }

    //根据ID查询订单
    @Override
    public Order selectById(Integer id) {
        return ordersMapper.selectById(id);
    }

    //删除订单
    @Override
    public boolean delete(Integer id) {
        return ordersMapper.delete(id) != 0;
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
    public boolean insert(Order order, Integer[] goods) throws XzroException {
        ordersMapper.insert(order);
        if (goods.length != 0) {
            //库存处理
            for (Integer good : goods) {
                //获取商品
                Good getGood = goodsService.selectById(good);
                //判断是否存在
                if (getGood == null) {
                    throw new XzroException("商品不存在");
                }
                //判断库存
                if (getGood.getStock() <= 0) {
                    throw new XzroException("库存不足");
                }
                //获取商品组
                List<Integer> collect = getGood.getGgroups().stream()
                        .map(Ggroup::getId)
                        .collect(Collectors.toList());
                Integer[] goodList = collect.toArray(collect.toArray(new Integer[0]));
                //库存操作
                //修改库存
                getGood.setStock(getGood.getStock() - 1);
                //增加销量
                getGood.setSalesVolume(getGood.getSalesVolume() + 1);
                goodsService.update(getGood, goodList);


            }
            ordersMapper.insertAssociate(order.getId(), goods);
        }
        return true;
    }


}
