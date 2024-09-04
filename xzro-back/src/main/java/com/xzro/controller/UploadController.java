package com.xzro.controller;

import com.xzro.bean.RespBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * ClassName: UploadController
 * Package: com.xzro.controller
 * Description:
 *
 * @Author Xzro
 * @Create 2024/8/31 17:51
 * @Version 1.0
 */
@CrossOrigin
@RestController
@RequestMapping("/api/upload")
public class UploadController {
    @Value("${xzro.picdir}")
    private String picdir;

    @PostMapping
    public RespBean upload(MultipartFile pic) throws IOException {
        String filename = UUID.randomUUID().toString().replace("-","") + "_" + pic.getOriginalFilename();
        //如何保证图片的名字唯一
        pic.transferTo(new File(picdir+filename));
        return RespBean.ok("上传成功",filename);
    }
}
