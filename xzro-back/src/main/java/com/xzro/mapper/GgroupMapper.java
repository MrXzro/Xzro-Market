package com.xzro.mapper;

import com.xzro.bean.Ggroup;

import java.util.List;

/**
 * ClassName: GgroupMapper
 * Package: com.xzro.mapper
 * Description:
 *      商品组管理
 * @Author Xzro
 * @Create 2024/9/3 19:54
 * @Version 1.0
 */
public interface GgroupMapper {
    //TODO:软删除分组下商品
    //TODO:批量加入分组
    //查询所有商品组
    List<Ggroup> selectAll();
    //根据ID查询所有组
    Ggroup selectById(Integer id);
    //删除商品组
    int delete(Integer id);

    //更新商品组
    int update(Ggroup ggroup);

    //添加商品组
    int insert(Ggroup ggroup);

}
