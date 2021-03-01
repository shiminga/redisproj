package com.consumer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebappConfig implements WebMvcConfigurer {

    @Bean
    public Intecepter  interceptorConfig(){
        return  new Intecepter();
    }
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //注册自定义拦截器，添加拦截路径和排除拦截路径
        registry.addInterceptor(interceptorConfig()).addPathPatterns("/**");
//                .excludePathPatterns("/mobile/hr/saveApplyEmployee");
    }
}
