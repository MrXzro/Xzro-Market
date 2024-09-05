package com.xzro.service.impl;

import com.xzro.bean.Ggroup;
import com.xzro.mapper.GgroupMapper;
import com.xzro.service.GgroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: GgroupsServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/4 16:16
 * @Version 1.0
 */
@Service
public class GgroupsServiceImpl implements GgroupsService {
    @Autowired
    private GgroupMapper ggroupMapper;
    @Override
    public List<Ggroup> selectAll() {
        return ggroupMapper.selectAll();
    }

    @Override
    public Ggroup selectById(Integer id) {
        return ggroupMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean delete(Integer id) {
        ggroupMapper.delete(id);
        ggroupMapper.deleteAssociate(id);
        return true;
    }

    @Override
    public boolean insert(Ggroup ggroup) {
        return ggroupMapper.insert(ggroup) != 0;
    }

    @Override
    public boolean update(Ggroup ggroup) {
        return ggroupMapper.update(ggroup) != 0;
    }
}
