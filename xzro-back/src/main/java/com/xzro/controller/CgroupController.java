package com.xzro.controller;

import com.xzro.bean.Cgroup;
import com.xzro.bean.RespBean;
import com.xzro.service.CgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * ClassName: CgroupController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/5 14:33
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/api/cgroups")
public class CgroupController {
    @Autowired
    private CgroupService cgroupService;
    @GetMapping("/selectAll")
    public RespBean selectAll() {
        List<Cgroup> cgroups = cgroupService.selectAll();
        return RespBean.ok("查询成功", cgroups);
    }
}
