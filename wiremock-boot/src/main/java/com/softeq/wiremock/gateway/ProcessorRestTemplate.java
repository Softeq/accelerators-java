package com.softeq.wiremock.gateway;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

import static java.util.Arrays.asList;

public class ProcessorRestTemplate extends RestTemplate {

    public ProcessorRestTemplate() {
        final ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter(objectMapper);
        converter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM));
        setMessageConverters(asList(converter));

        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();

        factory.setConnectTimeout(1000);
        factory.setReadTimeout(1000);

        setRequestFactory(factory);
    }
}
