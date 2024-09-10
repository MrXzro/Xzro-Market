package com.xzro.controller.admin;

import com.xzro.bean.RespBean;
import com.xzro.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * ClassName: InfoController
 * Package: com.xzro.controller.admin
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/10 9:43
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/api/info")
public class InfoController {
    @Autowired
    private InfoService infoService;
    @GetMapping
    public RespBean getInfo(){
        Map<String, Object> stringObjectMap = infoService.selectInfo();
        return RespBean.ok("查询成功", stringObjectMap);
    }
}
