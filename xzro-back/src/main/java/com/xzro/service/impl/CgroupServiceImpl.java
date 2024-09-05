package com.xzro.service.impl;

import com.xzro.bean.Cgroup;
import com.xzro.mapper.CgroupMapper;
import com.xzro.mapper.GgroupMapper;
import com.xzro.service.CgroupService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Override
    public List<Cgroup> selectAll() {
        return cgroupMapper.selectAll("");
    }
}
