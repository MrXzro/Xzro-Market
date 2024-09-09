package com.xzro.mapper;

import com.xzro.bean.Customer;
import com.xzro.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * ClassName: UploadDao
 * Package: com.xzro.mapper
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/9 11:43
 * @Version 1.0
 */
@Repository
public class UploadDao {
    @Autowired
    private UploadMapper uploadMapper;
    @Autowired
    private CustomerService customerService;
    public void save(List<Customer> list) {
        list.stream().filter(item -> customerService.selectByUsername(item.getUsername()) == null).forEach(item ->{
            item.setPassword("292f14a0373365a964d3a9aa874c2ba7");
        });
        uploadMapper.batchInsert(list);
    }
}
