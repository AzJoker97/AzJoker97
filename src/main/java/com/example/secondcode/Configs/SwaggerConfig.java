package com.example.secondcode.Configs;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {
//    创建一个容器
    @Bean
    public OpenAPI simpleOpenApi(){
        return new OpenAPI()
                .info(new Info().title("User-Api")
                        .description("基本文档")
                        .version("V1")
                        .license(new License().name("api 2.0")))
                .externalDocs(new ExternalDocumentation()
                        .description("外部文档")
                        .url("https://springshop.wiki.github.org/docs"));
    }

//    分组接口
/*    public GroupedOpenApi groupedOpenApi(){
        return
                GroupedOpenApi.builder().group("user").displayName("user")
                        .addOpenApiCustomizer(openApi -> openApi.info(new Info().title("USER 后台 API")
                                .version("1.0")))
                        .packagesToScan("com.example.secondcode.controller.UserController")
                        .pathsToMatch("/**")
                        .build();
    }*/
}
