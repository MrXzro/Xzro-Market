package com.xzro.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzro.bean.Order;
import com.xzro.bean.RespBean;
import com.xzro.service.GoodsService;
import com.xzro.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ClassName: OrdersController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/6 9:26
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/api/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @Autowired
    private GoodsService goodsService;

    //查询所有订单
    @GetMapping("/selectByPage/{currentPage}")
    public RespBean selectByPage(@PathVariable("currentPage") Integer currentPage) {
        PageHelper.startPage(currentPage, 10);
        List<Order> orders = ordersService.selectAll();
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        return RespBean.ok("查询成功", orderPageInfo);
    }

    //根据ID查询订单
    @GetMapping("/selectById/{id}")
    public RespBean selectById(@PathVariable("id") Integer id) {
        Order order = ordersService.selectById(id);
        return RespBean.ok("查询成功", order);
    }
    //根据增加订单
    @PostMapping("/insertOrder")
    public RespBean insertOrder(@RequestBody Map<String,Object> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.convertValue(map.get("order"), Order.class);
        ArrayList<Order> list = (ArrayList<Order>) map.get("goods");
        Integer[] goods = list.toArray(new Integer[0]);
        //生成订单号
        String orderNo = DateUtil.format(new Date(), "yyyyMMddHHmmss" )+ IdUtil.simpleUUID();
        order.setOrderNo(orderNo);
        //计算付款金额
        if (goods.length==0){
            return RespBean.error("添加失败，未选择商品");
        }
        //初始化金额
        BigDecimal sum = new BigDecimal(0);
        for (Integer good : goods) {
            sum = sum.add(goodsService.selectById(good).getPrice());
        }
        order.setPaidPrice(sum);
        if (ordersService.insert(order, goods)) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }
    //修改订单
    @PostMapping("/updateOrder")
    public RespBean updateOrder(@RequestBody Map<String,Object> map) {

        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.convertValue(map.get("order"), Order.class);
        ArrayList<Order> list = (ArrayList<Order>) map.get("goods");
        Integer[] goods = list.toArray(new Integer[0]);
        //计算付款金额
        if (goods.length==0){
            return RespBean.error("添加失败，未选择商品");
        }
        //初始化金额
        BigDecimal sum = new BigDecimal(0);
        for (Integer good : goods) {
            sum = sum.add(goodsService.selectById(good).getPrice());
        }
        order.setPaidPrice(sum);
        if (ordersService.update(order, goods)) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }
    //删除订单
    @PostMapping("/deleteById")
    public RespBean deleteById(@RequestBody Map<String,Object> map) {
        if (ordersService.delete((Integer) map.get("id"))) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
}
