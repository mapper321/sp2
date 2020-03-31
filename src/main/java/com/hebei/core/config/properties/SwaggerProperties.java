package com.hebei.core.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: mapper
 * @since: 2020/3/31
 */
@ConfigurationProperties(prefix = "swagger")
@Getter
@Setter
public class SwaggerProperties {

    private String serviceName;

    private String description;

    private String developer;

    private String url;

    private String email;
}
