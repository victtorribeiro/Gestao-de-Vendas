package com.curso.api.gestaovendas.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket config(){
        return new Docket(DocumentationType.OAS_30).select()
                .apis(RequestHandlerSelectors.basePackage("com.curso.api"))
                .build().apiInfo(informAPI());
    }

    private ApiInfo informAPI(){
        return new ApiInfoBuilder().title("Gestão de Vendas")
                .description("Sistema de gestão de vendas")
                .version("1.0.0").build();
    }
}
