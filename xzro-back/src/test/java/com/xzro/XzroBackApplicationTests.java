package com.xzro;

import com.xzro.mapper.CustomerMapper;
import com.xzro.mapper.GoodsMapper;
import com.xzro.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class XzroBackApplicationTests {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrdersMapper ordersMapper;
   @Test
   public void test1(){
       System.out.println(goodsMapper.selectById(1));
   }
   @Test
   public void test2(){
       ordersMapper.selectAll("").forEach(System.out::println);
   }

}
