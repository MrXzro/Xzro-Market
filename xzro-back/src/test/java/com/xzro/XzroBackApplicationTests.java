package com.xzro;

import com.xzro.mapper.CustomerMapper;
import com.xzro.mapper.GoodsMapper;
import com.xzro.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

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
       System.out.println(goodsMapper.selectByGroup(1));
   }
   @Test
   public void test2(){
//       ordersMapper.selectAll("").forEach(System.out::println);
   }

   @Test
   public void test3(){
       BigDecimal sum = new BigDecimal(0);
       for (int i = 0; i<10;i++) {
           sum = sum.add(new BigDecimal(1));
       }
       System.out.println(sum);

   }

}
