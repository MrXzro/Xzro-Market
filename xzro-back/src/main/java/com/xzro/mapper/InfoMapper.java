package com.xzro.mapper;

import java.math.BigDecimal;

/**
 * ClassName: InfoMapper
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/10 9:28
 * @Version 1.0
 */
public interface InfoMapper {
    //查询所有会员数量
    Integer selectMemberNum();
    //统计订单数
    Integer selectOrderNum();
    //查询商品数
    Integer selectGoodNum();
    //计算总收益
    BigDecimal selectAllIncome();
}
