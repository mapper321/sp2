package com.hebei.core.config;

import com.hebei.core.config.properties.SwaggerProperties;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger文档配置
 * @author: mapper
 * @since: 2020/3/31
 */
@Configuration
@EnableSwagger2
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfiguration {

    private  SwaggerProperties properties;

    public SwaggerConfiguration(SwaggerProperties properties){
        this.properties = properties;
    }

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(properties.getServiceName() + " Restful APIs")
                .description(properties.getDescription())
                .contact(new Contact(properties.getDeveloper(), properties.getUrl(), properties.getEmail())).version("1.0").build();
    }
}
