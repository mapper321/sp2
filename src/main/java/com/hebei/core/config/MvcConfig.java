package com.hebei.core.config;

import com.hebei.core.web.interceptor.AuthorizationInterceptor;
import com.hebei.core.web.servlet.GridFsServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 跨域配置
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT").maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new AuthorizationInterceptor());
    }

    @Bean
    public ServletRegistrationBean getServletRegistrationBean() { // 一定要返回ServletRegistrationBean
        ServletRegistrationBean bean = new ServletRegistrationBean(new GridFsServlet()); // 放入自己的Servlet对象实例
        bean.addUrlMappings(GridFsServlet.URI_PREFIX + "*"); // 访问路径拦截规则
        return bean;
    }
}
