package com.xzro.service;

import com.xzro.bean.Ggroup;

import java.util.List;

/**
 * ClassName: GgroupsService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/4 16:15
 * @Version 1.0
 */
public interface GgroupsService {
    //查询所有分组
    List<Ggroup> selectAll();

    //删除分组
    boolean delete(Integer id);

    //新增商品分组
    boolean insert(Ggroup ggroup);

    //修改商品分组
    boolean update(Ggroup ggroup);
}
