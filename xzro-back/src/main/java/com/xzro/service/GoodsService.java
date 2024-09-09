package com.xzro.service;

import com.xzro.bean.Good;

import java.util.List;

/**
 * ClassName: GoodsService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 11:15
 * @Version 1.0
 */
public interface GoodsService {
    //查询所有
    List<Good> selectAll(String name);
    //根据名称查询
    List<Good> selectByName(String name);

    //根据分组查询
    List<Good> selectByGroup(Integer gid);
    //根据ID查询
    Good selectById(Integer id);

    //增加商品
    boolean insert(Good good, Integer[] group);


    //根据ID删除
    boolean delete(Integer id);

    //修改商品(事务)
    boolean update(Good good, Integer[] group);

    //获取商品及其销量前五
    List<Good> selectGoodsSelas();
    //获取商品库存后五
    List<Good> selectGoodsStock();

}
