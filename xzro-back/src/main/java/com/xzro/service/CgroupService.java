package com.xzro.service;

import com.xzro.bean.Cgroup;

import java.util.List;

/**
 * ClassName: CgroupService
 * Package: com.xzro.service
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/5 14:28
 * @Version 1.0
 */
public interface CgroupService {
    //TODO:根据姓名查询
    //查询所有分组
    List<Cgroup> selectAll();

    //根据ID查询分组
    Cgroup selectById(Integer id);

    //根据ID删除分组
    boolean delete(Integer id);

    //添加分组
    boolean insert(Cgroup cgroup);

    //修改分组
    boolean update(Cgroup cgroup);

}
