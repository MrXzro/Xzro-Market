package com.xzro.mapper;

import com.xzro.bean.Cgroup;

import java.util.List;

/**
 * ClassName: CgroupMapper
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 20:02
 * @Version 1.0
 */
public interface CgroupMapper {
    //TODO:软删除分组下成员
    //TODO:批量加入分组
    //查询所有分组
    List<Cgroup> selectAll(String name);

    //根据ID查询分组
    Cgroup selectById(Integer id);
    //删除分组(根据ID)
    int delete(Integer id);

    //更新分组
    int update(Cgroup cgroup);

    //增加分组
    int insert(Cgroup cgroup);

}
