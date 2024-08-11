package com.gongyuan.bookstore.interceptor;

import lombok.AllArgsConstructor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: gongyuan
 * @date: 2024/8/10 12:47
 */
@Configuration
@AllArgsConstructor
public class SpringWebConfiguration implements WebMvcConfigurer {

    private final TokenValidateInterceptor tokenValidateInterceptor;
    private final TokenAutoRefresherInterceptor tokenAutoRefresherInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenValidateInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .order(1);
        registry.addInterceptor(tokenAutoRefresherInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/user/login")
                .order(2);
    }


    @Bean
    public FilterRegistrationBean<TraceFilter> customFilter() {
        FilterRegistrationBean<TraceFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new TraceFilter());
        registrationBean.addUrlPatterns("/*");
        registrationBean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return registrationBean;
    }
}
