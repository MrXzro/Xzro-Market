package com.xzro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import java.util.List;

/**
 * ClassName: Ggroup
 * Package: com.xzro.bean
 * Description:
 *      商品组实体类
 * @Author Xzro
 * @Create 2024/9/2 23:09
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Ggroup {
    //商品组ID
    private Integer id;
    //商品组名称
    @NotBlank(message = "组名不能为空")
    private String name;
    //商品
    private List<Good> goods;
}

