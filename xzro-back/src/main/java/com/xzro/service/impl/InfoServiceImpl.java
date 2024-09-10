package com.xzro.service.impl;

import com.xzro.mapper.InfoMapper;
import com.xzro.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: InfoServiceImpl
 * Package: com.xzro.service.impl
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/10 9:39
 * @Version 1.0
 */
@Service
public class InfoServiceImpl implements InfoService {
    @Autowired
    private InfoMapper infoMapper;
    @Override
    public Map<String, Object> selectInfo() {
        Map<String, Object> map = new HashMap<>();
        map.put("memberNum", infoMapper.selectMemberNum());
        map.put("goodNum", infoMapper.selectGoodNum());
        map.put("orderNum", infoMapper.selectOrderNum());
        map.put("allIncome", infoMapper.selectAllIncome());
        return map;
    }
}
