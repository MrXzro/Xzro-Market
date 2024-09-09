package com.xzro.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * ClassName: Admin
 * Package: com.xzro.bean
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/2 23:06
 * @Version 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Admin {
    //管理员ID
    private Integer id;
    //管理员用户名
    @NotNull(message = "用户账号不能为空")
    private String username;
    //管理员密码
    @NotNull(message = "用户密码不能为空")
    private String password;
}
