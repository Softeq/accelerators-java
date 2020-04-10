/*
 * Developed by Softeq Development Corporation
 * http://www.softeq.com
 */

package com.softeq.accelerator.flyway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

/**
 * Configuring swagger.
 * <p/>
 * Created on 4/6/2020.
 * <p/>
 *
 * @author slapitsky
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api() {
        ResponseMessage internalError = new ResponseMessageBuilder()
            .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
            .message(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
            .build();
        ResponseMessage authError = new ResponseMessageBuilder()
            .code(HttpStatus.FORBIDDEN.value())
            .message(HttpStatus.FORBIDDEN.getReasonPhrase())
            .build();
        ResponseMessage formatError = new ResponseMessageBuilder()
            .code(HttpStatus.UNPROCESSABLE_ENTITY.value())
            .message(HttpStatus.UNPROCESSABLE_ENTITY.getReasonPhrase())
            .build();

        List<ResponseMessage> challengeErrors = List.of(internalError);
        List<ResponseMessage> reportErrors = List.of(internalError, authError, formatError);

        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .useDefaultResponseMessages(false)
            .globalResponseMessage(RequestMethod.GET, challengeErrors)
            .globalResponseMessage(RequestMethod.POST, reportErrors)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.softeq.accelerator.flyway.controller"))
            .paths(PathSelectors.any())
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Softeq Flyway accelerator REST API")
            .description("Softeq Flyway accelerator REST API")
            .version("1.0")
            .build();
    }
}
