package com.wengzhoujun.vechat.config;

import com.wengzhoujun.vechat.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created on 2019/7/9.
 *
 * @author WengZhoujun
 */
@Configuration
public class WebMvcConfg implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Autowired
    private IgnoredUrlsConfig ignoredUrlsConfig;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor).excludePathPatterns(ignoredUrlsConfig.getUrls());
    }
}
