package com.hebei.core.config;

import com.hebei.core.service.template.MyGridFsTemplate;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;

@Configuration
@AutoConfigureAfter(MongoAutoConfiguration.class)
public class GridFsConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MyGridFsTemplate myGridFsTemplate(MongoDbFactory mongoDbFactory,
                                             MongoTemplate mongoTemplate) {
        return new MyGridFsTemplate(
                mongoDbFactory,
                mongoTemplate.getConverter());
    }


}
