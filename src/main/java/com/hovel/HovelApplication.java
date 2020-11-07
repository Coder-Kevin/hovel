package com.hovel;

import com.hovel.base.thread.threadlocal.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@ServletComponentScan
@EnableAspectJAutoProxy
public class HovelApplication implements WebMvcConfigurer {

    public static void main(String[] args) {
        SpringApplication.run(HovelApplication.class, args);
    }


    @Bean //将组件注册在容器中
    public WebMvcConfigurer webMvcConfigurerAdapter() {
        return new WebMvcConfigurer() {
            //注册拦截器
            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(new HttpInterceptor())
                        .addPathPatterns("/**");
            }
        };
    }

}
