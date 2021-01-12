package com.xroad.testboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author : Xroad
 * @CreateTime : 2021/1/6
 * @Description :
 **/
@Configuration
public class ResourceConfig implements WebMvcConfigurer {

    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String path = System.getProperty("user.dir") + "src\\main\\resources\\static\\upload\\";



        //配置静态资源访问路径
//        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");


        registry.addResourceHandler("/upload/**").addResourceLocations("file:"+path);
    }


}
