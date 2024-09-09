package com.xzro.bean;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * ClassName: Order
 * Package: com.xzro.bean
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 8:48
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ExcelIgnoreUnannotated
public class Order {
    //订单ID
    private Integer id;
    //订单号
    private String orderNo;
    //订单状态
    private Integer status;
    //付款金额
    private BigDecimal paidPrice;
    //付款方式
    private String paymentMethod;
    //客户ID
    private Integer customerId;
    //客户
    private Customer customer;
    //创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date startDate;
    //结束时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endDate;
    //商品
    private List<Good> goods;

}
