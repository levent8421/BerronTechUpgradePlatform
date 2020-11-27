package com.berrontech.upgrade.web.admin.interceptor;

import com.berrontech.upgrade.web.commons.interceptor.HttpRequestLogInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Create By Levent8421
 * Create Time: 2020/8/26 1:12
 * Class Name: InterceptorConfiguration
 * Author: Levent8421
 * Description:
 * 拦截器配置组件
 *
 * @author Levent8421
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    private final UserTokenInterceptor userTokenInterceptor;
    private final HttpRequestLogInterceptor httpRequestLogInterceptor;

    public InterceptorConfiguration(HttpRequestLogInterceptor httpRequestLogInterceptor, UserTokenInterceptor userTokenInterceptor) {
        this.httpRequestLogInterceptor = httpRequestLogInterceptor;
        this.userTokenInterceptor = userTokenInterceptor;
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userTokenInterceptor);
        registry.addInterceptor(httpRequestLogInterceptor);
    }
}
