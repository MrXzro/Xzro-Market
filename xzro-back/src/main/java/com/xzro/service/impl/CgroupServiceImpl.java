package com.xzro.service.impl;

import com.xzro.bean.Cgroup;
import com.xzro.mapper.CgroupMapper;
import com.xzro.mapper.GgroupMapper;
import com.xzro.service.CgroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * ClassName: CgroupServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/5 14:32
 * @Version 1.0
 */
@Service
public class CgroupServiceImpl implements CgroupService {
    @Autowired
    private CgroupMapper cgroupMapper;
    //查询所有
    @Override
    @Cacheable(value = "supermarket-g", key = "'ggroupList'", unless = "#result==null || #result.size()==0")
    public List<Cgroup> selectAll() {
        return cgroupMapper.selectAll("");
    }
    //根据ID查询
    @Override
    public Cgroup selectById(Integer id) {
        return cgroupMapper.selectById(id);
    }
    //根据ID删除
    @Override
    @CacheEvict(value = "supermarket-g", key = "'ggroupList'" )
    public boolean delete(Integer id) {
        return cgroupMapper.delete(id) != 0;
    }
    //添加分组
    @Override
    @CacheEvict(value = "supermarket-g", key = "'ggroupList'" )
    public boolean insert(Cgroup cgroup) {
        return cgroupMapper.insert(cgroup) != 0;
    }
    //修改分组
    @Override
    @CacheEvict(value = "supermarket-g", key = "'ggroupList'" )
    public boolean update(Cgroup cgroup) {
        return cgroupMapper.update(cgroup)!=0;
    }

}
