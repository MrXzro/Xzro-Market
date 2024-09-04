package com.xzro.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * ClassName: MainConfig
 * Package: com.xzro.config
 * Description:
 *
 * @Author Xzro
 * @Create 2024/9/3 9:42
 * @Version 1.0
 */
@Configuration
public class MainConfig implements WebMvcConfigurer {
    @Value("${xzro.picdir}")
    private String picdir;
    //    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**");
//    }
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new JwtInterceptor())
//                .addPathPatterns("/admin/**")
//                .excludePathPatterns("/adminController/login");
//    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + picdir);
    }
}