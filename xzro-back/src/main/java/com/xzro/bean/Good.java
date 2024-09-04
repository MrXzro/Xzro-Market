package com.xzro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

/**
 * ClassName: Good
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
public class Good {
    //商品ID
    private Integer id;
    //商品名称
    private String name;
    //商品图片
    private String img;
    //商品描述
    private String description;
    //商品成本
    private BigDecimal cost;
    //商品价格
    private BigDecimal price;
    //销量
    private Integer salesVolume;
    //是否上架
    private Integer status;
    //库存
    private Integer stock;
    //商品组
    private List<Ggroup> ggroups;
}
