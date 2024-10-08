package com.xzro.controller.admin;

import cn.hutool.crypto.SecureUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzro.bean.Customer;
import com.xzro.bean.RespBean;
import com.xzro.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    @GetMapping("/selectByPage")
    public RespBean selectByPage(Integer currentPage, String name,  Boolean all) {
        if (all){
            List<Customer> customers = customerService.selectAll(name);
            return RespBean.ok("查询成功",customers);
        }
        PageHelper.startPage(currentPage, 10);
        List<Customer> customers = customerService.selectAll(name);
        PageInfo<Customer> customersInfo = new PageInfo<>(customers);
        return RespBean.ok("查询成功",customersInfo);
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
    //根据分组查询用户接口
    @GetMapping("/selectByGroup/{id}/{currentPage}")
    public RespBean selectByGroup(@PathVariable("id") Integer id, @PathVariable("currentPage") Integer currentPage) {
        PageHelper.startPage(currentPage, 10);
        List<Customer> customers = customerService.selectByGroup(id);
        PageInfo<Customer> customerPageInfo = new PageInfo<>(customers);
        return RespBean.ok("查询成功", customerPageInfo);
    }

    //用户删除接口
    @PostMapping("/deleteById")
    public RespBean delete(@RequestBody Map<String,Object> map){
        if (customerService.delete((Integer) map.get("id"))) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    //新增用户接口
    @PostMapping("/insertCustomer")
    public RespBean insert(@Valid @RequestBody Customer customer) {
        customer.setPassword(SecureUtil.md5(SecureUtil.md5(customer.getPassword())));
        if (customerService.insert(customer)) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }
    //修改用户接口
    @PostMapping("/updateCustomer")
    public RespBean update(@Valid @RequestBody Customer customer){
        if (customerService.update(customer)) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }
}
