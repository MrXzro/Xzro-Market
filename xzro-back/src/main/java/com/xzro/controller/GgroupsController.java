package com.xzro.controller;

import com.xzro.bean.Ggroup;
import com.xzro.bean.RespBean;
import com.xzro.service.GgroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
    //查询所有
    @GetMapping("/selectAll")
    public RespBean selectAll() {
        List<Ggroup> ggroups = ggroupsService.selectAll();
        return RespBean.ok("查询成功", ggroups);
    }

    //根据ID查询分组
    @GetMapping("/selectById/{id}")
    public RespBean selectById(@PathVariable("id") Integer id) {
        Ggroup ggroup = ggroupsService.selectById(id);
        return RespBean.ok("查询成功", ggroup);
    }
    //根据ID删除
    @PostMapping("/deleteById")
    private RespBean delete(@RequestBody Map<String,Object> map) {
        if (ggroupsService.delete((Integer)map.get("id"))){
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }

    //添加商品分组
    @PostMapping("/insertGgroup")
    private RespBean insertGgroup(@RequestBody Ggroup ggroup) {
        if (ggroupsService.insert(ggroup)) {
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }
    //修改商品分组
    @PostMapping("/updateGgroup")
    private RespBean updateGgroup(@RequestBody Ggroup ggroup){
        if (ggroupsService.update(ggroup)){
            return RespBean.ok("修改成功");
        }
        return RespBean.error("修改失败");
    }
}
