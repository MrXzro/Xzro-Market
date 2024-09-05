package com.xzro.controller;

import com.xzro.bean.Customer;
import com.xzro.bean.RespBean;
import com.xzro.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * ClassName: CustomerController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/5 11:17
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    //查询所有用户接口
    @GetMapping("/selectAll")
    public RespBean selectAll() {
        List<Customer> customers = customerService.selectAll();
        return RespBean.ok("查询成功",customers);
    }

    //根据ID查询用户接口
    @GetMapping("/selectById/{id}")
    public RespBean selectById(@PathVariable("id") Integer id) {
        Customer customer = customerService.selectById(id);
        if (customer != null) {
            return RespBean.ok("查询成功", customer);
        }
        return RespBean.error("查询失败");
    }


}
