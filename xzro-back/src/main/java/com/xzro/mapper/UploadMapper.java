package com.xzro.mapper;

import com.xzro.bean.Customer;

import java.util.List;

/**
 * ClassName: UploadMapper
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/9 11:36
 * @Version 1.0
 */
public interface UploadMapper {
    int batchInsert(List<Customer> list);
}
