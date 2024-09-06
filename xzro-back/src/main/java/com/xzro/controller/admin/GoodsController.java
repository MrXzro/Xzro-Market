package com.xzro.controller.admin;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xzro.bean.Good;
import com.xzro.bean.RespBean;
import com.xzro.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ClassName: GoodsController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 20:43
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/api/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;
    @GetMapping("/selectByPage")
    public RespBean selectByPage(Integer currentPage,String name){
        PageHelper.startPage(currentPage, 10);
        List<Good> goods = goodsService.selectAll(name);
        PageInfo<Good> goodPageInfo = new PageInfo<>(goods);
        return RespBean.ok("", goodPageInfo);
    }
    @GetMapping("/selectById/{id}")
    public RespBean selectById(@PathVariable("id") Integer id) {
        Good good = goodsService.selectById(id);
        return RespBean.ok("查询成功", good);
    }
    @GetMapping("/selectByGroup/{id}/{currentPage}")
    public RespBean selectByGroup(@PathVariable("id") Integer id, @PathVariable("currentPage") Integer currentPage){
        if (id!=0){
            PageHelper.startPage(currentPage, 10);
            List<Good> goods = goodsService.selectByGroup(id);
            PageInfo<Good> goodPageInfo = new PageInfo<>(goods);
            return RespBean.ok("查询成功", goodPageInfo);
        }
        List<Good> goods = goodsService.selectAll("");
        return RespBean.ok("查询成功", goods);
    }

    @PostMapping("/deleteById")
    public RespBean deleteById(@RequestBody Map<String, Object> map) {
        if (goodsService.delete((Integer) map.get("id"))) {
            return RespBean.ok("删除成功");
        }
        return RespBean.error("删除失败");
    }
    //更新商品
    @PostMapping("/updateGood")
    public RespBean updateGood(@RequestBody Map<String,Object> map){
        // 创建ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();
        // 将Object转换为Good对象

        Good good = objectMapper.convertValue(map.get("good"), Good.class);
        // 将 groups 转换为 List<Integer>
        List<Integer> groupsList = (List<Integer>) map.get("groups");

        // 将 List<Integer> 转换为 Integer[] 数组
        Integer[] groups = groupsList.toArray(new Integer[0]);
        if (goodsService.update(good,groups)){
             return RespBean.ok("修改成功");
         }
        return RespBean.error("修改失败");
    }
    //新增商品
    @PostMapping("/addGood")
    public RespBean addGood(@RequestBody Map<String,Object> map){
        // 创建ObjectMapper实例
        ObjectMapper objectMapper = new ObjectMapper();
        // 将Object转换为Good对象

        Good good = objectMapper.convertValue(map.get("good"), Good.class);
        // 将 groups 转换为 List<Integer>
        List<Integer> groupsList = (List<Integer>) map.get("groups");

        // 将 List<Integer> 转换为 Integer[] 数组
        Integer[] groups = groupsList.toArray(new Integer[0]);
        if (goodsService.insert(good,groups)){
            return RespBean.ok("添加成功");
        }
        return RespBean.error("添加失败");
    }





}
