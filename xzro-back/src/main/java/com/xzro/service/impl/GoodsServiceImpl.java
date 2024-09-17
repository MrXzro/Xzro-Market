package com.xzro.service.impl;

import com.xzro.bean.Good;
import com.xzro.mapper.GoodsMapper;
import com.xzro.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
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
    @Cacheable(value = "supermarket-g", key = "'goodList'", unless = "#result==null || #result.size()==0")
    public List<Good> selectAll(String name) {
        return goodsMapper.selectAll(name);
    }

    @Override
    @Cacheable(value = "supermarket-g", key = "'goodList'", unless = "#result==null || #result.size()==0")
    public List<Good> selectByName(String name) {
        return goodsMapper.selectAll(name);
    }

    @Override
    @Cacheable(value = "supermarket-g", key = "'goodList'", unless = "#result==null || #result.size()==0")
    public List<Good> selectByGroup(Integer gid) {
        return goodsMapper.selectByGroup(gid);
    }


    @Override
    @Transactional
    @CacheEvict(value = "supermarket-g", key = "'goodList'" )
    public boolean insert(Good good, Integer[] group) {
        goodsMapper.insert(good);
        if (group.length != 0) {
            goodsMapper.insertAssociate(good.getId(), group);
        }
        return true;
    }

    @Override
    @CacheEvict(value = "supermarket-g", key = "'goodList'" )
    public boolean delete(Integer id) {
        return goodsMapper.deleteGood(id) != 0;
    }

    @Override
    @CacheEvict(value = "supermarket-g", key = "'goodList'" )
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

    @Override
    public List<Good> selectGoodsSelas() {
        return goodsMapper.selectGoodsSelas();
    }

    @Override
    public List<Good> selectGoodsStock() {
        return goodsMapper.selectGoodsStock();
    }
}
