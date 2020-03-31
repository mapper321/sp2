package com.hebei.core.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;

@Configuration
public class JacksonConfig {
	 	@Bean
	    @Primary
	    @ConditionalOnMissingBean(ObjectMapper.class)
	    public ObjectMapper jacksonObjectMapper(Jackson2ObjectMapperBuilder builder) {
	        ObjectMapper objectMapper = builder.createXmlMapper(false).build();
	        //允许保留特殊字符如 #
	        objectMapper.configure(Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
	        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
	            @Override
	            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonProcessingException {
	                jsonGenerator.writeString("");
	            }
	        });
	        return objectMapper;
	    }
}
