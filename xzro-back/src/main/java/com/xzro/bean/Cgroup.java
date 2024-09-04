package com.xzro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
public class Cgroup {
    //用户组ID
    private Integer id;
    //组名
    private String name;
}
