package cn.center.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author song
 * @title http://localhost:8080/swagger-ui.html
 * @projectName learn-note-springboot2-job-quartz-v3
 * @description TODO
 * @date 2019年12月6日 下午10:19:24
 */
@Configuration
@EnableSwagger2
public class Swagger2 {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("cn.center.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
    	
        return new ApiInfoBuilder()
                .title("任务调度服务 API")
                .description("网址地址：任务调度管理中心")
                .termsOfServiceUrl("")
                .contact(new Contact("Y.T.C", "", "@qq.com"))
                .version("1.0")
                .build();
    }
}
