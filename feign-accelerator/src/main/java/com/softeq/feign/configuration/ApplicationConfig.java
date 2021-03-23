/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.feign.configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class ApplicationConfig {
    @Primary
    @Bean
    public MappingJackson2HttpMessageConverter jackson2Converter() {
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.getObjectMapper().configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        return converter;
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }

    @Bean
    public Decoder decoder(){
        return new JacksonDecoder(new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true));
    }

    @Bean
    public Encoder encoder(){
        return new JacksonEncoder(new ObjectMapper()
            .setSerializationInclusion(JsonInclude.Include.NON_NULL)
            .configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true));
    }

}
