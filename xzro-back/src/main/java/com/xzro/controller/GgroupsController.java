package com.xzro.controller;

import com.xzro.bean.Ggroup;
import com.xzro.bean.RespBean;
import com.xzro.service.GgroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * ClassName: GroupsController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/4 16:14
 * @Version 1.0
 */
@RestController
@CrossOrigin
@RequestMapping("/api/ggroups")
public class GgroupsController {
    @Autowired
    private GgroupsService ggroupsService;
    @GetMapping("/selectAll")
    public RespBean selectAll() {
        List<Ggroup> ggroups = ggroupsService.selectAll();
        return RespBean.ok("查询成功", ggroups);
    }
    @PostMapping
    private RespBean delete() {
        return RespBean.ok("删除成功");

    }
}
