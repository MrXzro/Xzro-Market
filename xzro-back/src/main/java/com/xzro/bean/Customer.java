package com.xzro.bean;

import com.alibaba.excel.annotation.ExcelIgnoreUnannotated;
import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
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
@ExcelIgnoreUnannotated
@Validated
public class Customer {
    //客户ID
    @ExcelProperty("客户ID")
    private Integer id;
    //用户名
    @NotBlank(message = "用户账号不能为空")
    @ExcelProperty("用户名")
    private String username;
    //密码
    @NotBlank(message = "用户密码不能为空")
    private String password;
    //头像
    private String img;
    //姓名
    @NotBlank(message = "姓名不能为空")
    @ExcelProperty("姓名")
    private String name;
    //性别
    @NotBlank(message = "性别不能为空")
    @ExcelProperty("性别")
    private String gender;
    //年龄
    @NotBlank(message = "年龄不能为空")
    @ExcelProperty("年龄")
    private Integer age;
    //电话
    @NotBlank(message = "电话不能为空")
    @ExcelProperty("电话")
    private String phone;
    //状态
    @NotBlank(message = "状态不能为空")
    @ExcelProperty("状态")
    private Integer status;
    //组ID
    @ExcelProperty("组ID")
    @NotBlank(message = "用户组不能为空")
    private Integer groupId;
    //用户组
    private Cgroup customerGroup;
    //订单
    private List<Order> orders;
}
