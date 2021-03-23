/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.feign.configuration;

import com.softeq.feign.client.WeatherClient;
import com.softeq.feign.exception.CustomErrorDecoder;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.codec.ErrorDecoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import javax.inject.Inject;

public class WeatherClientConfig {
    @Bean
    public Contract feignContract() {
        return new feign.Contract.Default();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorDecoder();
    }

    @Inject
    Encoder encoder;

    @Inject
    Decoder decoder;

    @Value("${softeq.url}")
    private String url;

    @Bean
    public WeatherClient feignClientRepository(){
        return Feign.builder()
            .client(new OkHttpClient())
            .encoder(encoder)
            .decoder(decoder)
            .errorDecoder(new CustomErrorDecoder())
            .logger(new Slf4jLogger(WeatherClient.class))
            .logLevel(Logger.Level.BASIC)
            .target(WeatherClient.class, url);
    }
}
