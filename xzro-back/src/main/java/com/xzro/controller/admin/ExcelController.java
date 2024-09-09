package com.xzro.controller.admin;

import com.alibaba.excel.EasyExcel;
import com.xzro.bean.Customer;
import com.xzro.bean.Order;
import com.xzro.service.CustomerService;
import com.xzro.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * ClassName: ExcelController
 * Package: com.xzro.controller.admin
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/9 9:45
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/api/excel")
public class ExcelController {
    @Autowired
    private CustomerService customerService;

//    http://localhost:8080/api/excel/download
    @GetMapping("download")
    public void download(HttpServletResponse response) throws IOException {
        // 这里注意 有同学反应使用swagger 会导致各种问题，请直接用浏览器或者用postman
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("utf-8");
        // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
        String fileName = URLEncoder.encode("客户表", "UTF-8").replaceAll("\\+", "%20");
        response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
        EasyExcel.write(response.getOutputStream(), Customer.class).sheet("客户表").doWrite(customerService.selectAll(""));
    }
}
