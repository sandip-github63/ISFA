package com.isfa.swagger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
@EnableWebMvc
//@EnableSwagger2
public class SwaggerConfig {
    
	private static final Logger logger = LoggerFactory.getLogger(SwaggerConfig.class);

	
    @Bean
    public Docket api() {
    	logger.info("SwaggerConfig docket api method executing");
        return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.isfa"))
            .paths(PathSelectors.any())
            .build()
            .apiInfo(apiInfo());
    }
    
    private ApiInfo apiInfo() {
    	logger.info("SwaggerConfig docket apiInfo method executing");
        return new ApiInfoBuilder()
            .title("ISFA Documentation")
            .description("API documentation for ISFA Api @denave.com")
            .version("1.0.0")
            .build();
    }
}

