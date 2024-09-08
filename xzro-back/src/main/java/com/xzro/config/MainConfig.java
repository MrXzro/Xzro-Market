package com.xzro.config;

import com.xzro.controller.user.CustomerLoginController;
import com.xzro.interceptor.AdminJwtInterceptor;
import com.xzro.interceptor.PublicJwtInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
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
//        @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(loginInterceptor).addPathPatterns("/admin/**");
//    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //管理员拦截
        registry.addInterceptor(new AdminJwtInterceptor())
                .addPathPatterns("/api/**")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/api/orders/selectByPage")
                .excludePathPatterns("/api/ggroups/selectAll")
                .excludePathPatterns("/api/goods/selectByGroup")
                .excludePathPatterns("/api/goods/selectByGroup")
                .excludePathPatterns("/api/orders/userInsertOrder")
                .excludePathPatterns("/api/orders/userPay")
                .excludePathPatterns("/api/orders/deleteById")
                .excludePathPatterns("/customer/login");
        registry.addInterceptor(new PublicJwtInterceptor())
                .addPathPatterns("/api/orders/selectByPage")
                .addPathPatterns("/api/orders/userInsertOrder")
                .addPathPatterns("/api/orders/userPay")
                .addPathPatterns("/api/orders/deleteById")
                .addPathPatterns("/api/ggroups/selectAll")
                .addPathPatterns("/api/orders/selectByPage");
    }


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/upload/**")
                .addResourceLocations("file:" + picdir);
    }
}