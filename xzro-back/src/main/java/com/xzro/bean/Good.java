package com.xzro.bean;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
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
@Validated
public class Good {
    //商品ID
    private Integer id;
    //商品名称
    @NotBlank(message = "商品名称不能为空")
    private String name;
    //商品图片
    private String img;
    //商品描述
    @NotBlank(message = "商品描述不能为空")
    private String description;
    //商品成本
    @NotBlank(message = "商品成本不能为空")
    private BigDecimal cost;
    //商品价格
    @NotBlank(message = "商品价格不能为空")
    private BigDecimal price;
    //销量
    @NotBlank(message = "销量不能为空")
    private Integer salesVolume;
    //是否上架
    @NotBlank(message = "状态不能为空")
    private Integer status;
    //库存
    @NotBlank(message = "库存不能为空")
    private Integer stock;
    //商品组
    private List<Ggroup> ggroups;

}
