package com.xzro.mapper;

import com.xzro.bean.Good;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ClassName: GoodsMapper
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 10:09
 * @Version 1.0
 */
public interface GoodsMapper {
    //根据ID查询
    Good selectById(Integer id);
    //查询所有商品（根据商品名称查询）
    List<Good> selectAll(String name);

    //根据分组ID查询商品
    List<Good> selectByGroup(Integer gid);
    //根据商品ID修改商品
    int updateGoods(Good good);


    //更新关系表中的数据
    int insertAssociate(@Param("id") Integer id, @Param("groups") Integer[] groups);
    //T根据商品ID删除商品
    int deleteGood(Integer id);

    //删除关系表中的数据
    int deleteAssociate(Integer id);
    //添加商品
    int insert(Good good);
    //获取商品及其销量前五
    List<Good> selectGoodsSelas();
    //获取商品库存后五
    List<Good> selectGoodsStock();
}
