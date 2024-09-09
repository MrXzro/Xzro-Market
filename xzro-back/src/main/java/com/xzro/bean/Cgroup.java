package com.xzro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

/**
 * ClassName: Cgroup
 * Package: com.xzro.bean
 * Description:
 *      客户分组实体类
 * @Author Xzro
 * @Create 2024/9/2 23:08
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class Cgroup {
    //用户组ID
    private Integer id;
    //组名
    @NotBlank(message = "组名不能为空")
    private String name;
}
