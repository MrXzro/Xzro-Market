package com.xzro.controller;

import com.xzro.bean.Cgroup;
import com.xzro.bean.RespBean;
import com.xzro.service.CgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    //查询所有分组
    @GetMapping("/selectAll")
    public RespBean selectAll() {
        List<Cgroup> cgroups = cgroupService.selectAll();
        return RespBean.ok("查询成功", cgroups);
    }

    //根据ID查询分组
    @GetMapping("/selectById/{id}")
    public RespBean selectById(@PathVariable("id") Integer id) {
        Cgroup cgroup = cgroupService.selectById(id);
        return RespBean.ok("查询成功",cgroup);
    }
    //根据ID删除
    @PostMapping("/deleteById")
    public RespBean selectById(@RequestBody Map<String,Object> map){
        if (cgroupService.delete((Integer) map.get("id"))) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    //添加分组
    @PostMapping("/insert")
    public RespBean insert(@RequestBody Cgroup cgroup) {
        if (cgroupService.insert(cgroup)) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }
    //修改分组
    @PostMapping("/update")
    public RespBean update(@RequestBody Cgroup cgroup){
        if (cgroupService.update(cgroup)) {
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }



}
