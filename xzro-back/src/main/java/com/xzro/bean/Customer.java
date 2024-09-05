package com.xzro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * ClassName: Customer
 * Package: com.xzro.bean
 * Description:
 *      客户实体类
 * @Author Xzro
 * @Create 2024/9/2 23:09
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    //客户ID
    private Integer id;
    //用户名
    private String username;
    //密码

    private String password;
    //头像
    private String img;
    //姓名
    private String name;
    //性别
    private String gender;
    //年龄
    private Integer age;
    //电话
    private String phone;
    //状态
    private Integer status;
    //组ID
    private Integer groupId;
    //用户组id
    private Cgroup customerGroup;
    //订单
    private List<Order> orders;
}
