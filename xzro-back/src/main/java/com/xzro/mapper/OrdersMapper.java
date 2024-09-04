package com.xzro.mapper;

import com.xzro.bean.Order;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: OrderMapper
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 15:16
 * @Version 1.0
 */
public interface OrdersMapper {
    //查询订单（订单号）
    List<Order> selectAll(String orderNo);
    //增加订单
    int insert(Order order);

    //添加订单关系
    int insertAssociate(@Param("id") Integer id, @Param("goods") Long[] goods);

    //删除订单关系
    int deleteAssociate(Integer id);

    //删除订单(根据ID)
    int delete(Integer id);
    //修改订单
    int update(@Param("order") Order order, @Param("goods") Integer[] goods);

}
