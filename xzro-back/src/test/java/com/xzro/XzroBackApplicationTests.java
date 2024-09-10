package com.xzro;

import cn.hutool.core.date.DateField;
import cn.hutool.core.date.DateUtil;
import com.xzro.mapper.CustomerMapper;
import com.xzro.mapper.GoodsMapper;
import com.xzro.mapper.InfoMapper;
import com.xzro.mapper.OrdersMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

@SpringBootTest
class XzroBackApplicationTests {
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private GoodsMapper goodsMapper;
    @Autowired
    private OrdersMapper ordersMapper;
    @Autowired
    private InfoMapper infoMapper;
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
   @Test
   public void test4(){
       Date startDate = DateUtil.date();
       //生成订单结束时间
       Date endDate = DateUtil.offset(startDate, DateField.MINUTE, 30);
       System.out.println(startDate);
       System.out.println(endDate);
   }

   @Test
   public void test5(){
       System.out.println(infoMapper.selectAllIncome());
   }
}
