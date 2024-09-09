package com.xzro.controller.admin;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzro.bean.Order;
import com.xzro.bean.RespBean;
import com.xzro.exception.XzroException;
import com.xzro.service.GoodsService;
import com.xzro.service.OrdersService;
import com.xzro.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
    @GetMapping("/selectByPage")
    public RespBean selectByPage(Integer currentPage, String orderNo, Integer id, HttpServletRequest request) {

        //获取token
        String token = request.getHeader("token");
        PageHelper.startPage(currentPage, 10);
        Integer customerId = id;
        try {

            //解析JWT，如果出现问题会抛出异常
            Map<String, Object> stringObjectMap = JwtUtils.parseJwtToMap(token);
            if (JwtUtils.verifyJwt(token)){
                //判断管理员权限
                if (!"admin".equals(stringObjectMap.get("isAdmin"))) {
                    customerId = (Integer) stringObjectMap.get("id");
                }
            }
        } catch (Exception e) {

            return RespBean.error("令牌无效111");
        }
        List<Order> orders = ordersService.selectAll(orderNo, customerId);
        PageInfo<Order> orderPageInfo = new PageInfo<>(orders);
        return RespBean.ok("查询成功", orderPageInfo);
    }

    //根据ID查询订单
    @GetMapping("/selectById/{id}")
    public RespBean selectById(@PathVariable("id") Integer id) {
        Order order = ordersService.selectById(id);
        return RespBean.ok("查询成功", order);
    }
    //增加订单
    @PostMapping("/insertOrder")
    public RespBean insertOrder(@RequestBody Map<String,Object> map) {
        ObjectMapper objectMapper = new ObjectMapper();
        Order order = objectMapper.convertValue(map.get("order"), Order.class);
        if (order.getPaymentMethod() == null) {
            return RespBean.error("付款方式不能为空！");
        }
        if (order.getStartDate() == null) {
            return RespBean.error("创建时间不能为空！");
        }
        if (order.getEndDate() == null) {
            return RespBean.error("结束时间不能为空！");
        }
        if (order.getStatus() == null) {
            return RespBean.error("订单状态不能为空！");
        }
        if (order.getCustomerId() == null) {
            return RespBean.error("订单客户不能为空！");
        }
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
        try {
            if (ordersService.insert(order, goods)) {
                return RespBean.ok("添加成功");
            }
        } catch (XzroException e) {
            return RespBean.error(e.getMessage());
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
    public RespBean deleteById(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        Integer orderId = (Integer) map.get("id");
        //获取token
        String token = request.getHeader("token");
        Integer customerId = null;
        try {

            //解析JWT，如果出现问题会抛出异常
            Map<String, Object> stringObjectMap = JwtUtils.parseJwtToMap(token);
            if (JwtUtils.verifyJwt(token)){
                //判断管理员权限
                if (!"admin".equals(stringObjectMap.get("isAdmin"))) {
                    customerId = (Integer) stringObjectMap.get("id");
                    //判断用户越权
                    //获取数据库中匹配的ID
                    Integer customerId1 = ordersService.selectById(orderId).getCustomerId();
                    if (!customerId.equals(customerId1)){
                        return RespBean.error("令牌无效");
                    }
                }
            }
        } catch (Exception e) {
            return RespBean.error("令牌无效");
        }


        if (ordersService.delete(orderId)) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
    //用户添加订单
    @PostMapping("/userInsertOrder")
    public RespBean userInsertOrder(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        //获取客户ID
        Integer customerId = null;
        try{
            String token = request.getHeader("token");
            Map<String, Object> stringObjectMap = JwtUtils.parseJwtToMap(token);
            customerId = (Integer) stringObjectMap.get("id");
        }catch (Exception e){
            return RespBean.error("令牌无效");
        }
        //创建空白订单
        Order order = new Order();
        //获取商品列表
        ArrayList<Order> list = (ArrayList<Order>) map.get("goods");
        Integer[] goods = list.toArray(new Integer[0]);
        //获取付款方式
        String paymentMethod = (String) map.get("paymentMethod");
        if (paymentMethod==null) {
            return RespBean.error("付款方式不能为空");
        }
        order.setPaymentMethod(paymentMethod);
        //生成订单号
        String orderNo = DateUtil.format(new Date(), "yyyyMMddHHmmss" )+ IdUtil.simpleUUID();
        order.setOrderNo(orderNo);
        //生成订单创建时间
        Date startDate = DateUtil.date();
        order.setStartDate(startDate);
        //生成订单结束时间
        Date endDate = DateUtil.offset(startDate, DateField.MINUTE, 30);
        order.setEndDate(endDate);
        //设置订单状态
        order.setStatus(0);
        //计算付款金额
        if (goods.length==0){
            return RespBean.error("添加失败，未选择商品");
        }
        //绑定客户ID
        order.setCustomerId(customerId);
        //初始化金额
        BigDecimal sum = new BigDecimal(0);
        for (Integer good : goods) {
            sum = sum.add(goodsService.selectById(good).getPrice());
        }
        order.setPaidPrice(sum);
        try {
            if (ordersService.insert(order, goods)) {
                return RespBean.ok("添加成功");
            }
        } catch (XzroException e) {
            return RespBean.error(e.getMessage());
        }
        return RespBean.error("添加失败");
    }

    //用户支付订单
    @PostMapping("/userPay")
    public RespBean userPay(@RequestBody Map<String,Object> map, HttpServletRequest request) {
        Integer orderId = (Integer) map.get("orderId");
        //获取订单
        Order order = ordersService.selectById(orderId);
        if (order == null) {
            return RespBean.error("该订单不存在");
        }
        if (order.getStatus() == 1) {
            return RespBean.error("该订单已支付");
        }
        //获取客户ID
        Integer customerId = null;
        try{
            String token = request.getHeader("token");
            Map<String, Object> stringObjectMap = JwtUtils.parseJwtToMap(token);
            customerId = (Integer) stringObjectMap.get("id");
        }catch (Exception e){
            RespBean.error("令牌无效");
        }
        if (!order.getCustomerId().equals(customerId)){
            return RespBean.error("令牌无效");
        }
        //设置已支付
        order.setStatus(1);
        //获取商品列表
        List<Integer> collect = order.getGoods().stream().map(item -> item.getId()).collect(Collectors.toList());
        Integer[] goods = collect.toArray(new Integer[0]);
        ordersService.update(order, goods);
        return RespBean.ok("支付成功");
    }
}
