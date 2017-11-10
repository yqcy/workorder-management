package com.yq.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * created by YQ on 2017-11-09
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    public static final String SWAGGER_SCAN_BASE_PACKAGE = "com.yq.controller";
    public static final String VERSION = "1.0.0";

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Swagger API")//标题
                .description("This is to show api description")//描述
                .license("百度一下")//描述下的超链接
                .licenseUrl("http://www.baidu.com")
                .termsOfServiceUrl("")
                .version(VERSION)//版本号
                .build();
    }

    @Bean
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("Restfull API")
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(true)
                .forCodeGeneration(false)
                .select()
                .apis(RequestHandlerSelectors.basePackage(SWAGGER_SCAN_BASE_PACKAGE))//通过包路径生成接口
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))//换成这种通过注解生成接口也可以
                .build()
                .apiInfo(apiInfo());
    }
}
