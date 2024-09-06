package com.xzro.service.impl;

import com.xzro.bean.Good;
import com.xzro.mapper.GoodsMapper;
import com.xzro.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ClassName: GoodsServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 20:34
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<Good> selectAll(String name) {
        return goodsMapper.selectAll(name);
    }

    @Override
    public List<Good> selectByName(String name) {
        return goodsMapper.selectAll(name);
    }

    @Override
    public List<Good> selectByGroup(Integer gid) {
        return goodsMapper.selectByGroup(gid);
    }


    @Override
    @Transactional
    public boolean insert(Good good, Integer[] group) {
        goodsMapper.insert(good);
        if (group.length != 0) {
            goodsMapper.insertAssociate(good.getId(), group);
        }
        return true;
    }

    @Override
    public boolean delete(Integer id) {
        return goodsMapper.deleteGood(id) != 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean update(Good good, Integer[] group) {
        goodsMapper.updateGoods(good);
        goodsMapper.deleteAssociate(good.getId());
        if (group.length != 0) {
            goodsMapper.insertAssociate(good.getId(), group);
        }
        return true;
    }

    public Good selectById(Integer id) {
        return goodsMapper.selectById(id);
    }
}
