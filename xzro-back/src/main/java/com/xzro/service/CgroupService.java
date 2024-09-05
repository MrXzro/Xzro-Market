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

}
