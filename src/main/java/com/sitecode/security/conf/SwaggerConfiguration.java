package com.sitecode.security.conf;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.ApiKeyVehicle;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@PropertySource("classpath:swagger.properties")
public class SwaggerConfiguration {
	
	@Autowired
	private Environment env;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        	.apiInfo(apiInfo())
            .enable(true) //colocar FALSE si no se quiere mostrar el API Swagger
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.sitecode"))
            .paths(regex(env.getProperty("swagger.path.regex")))
            .build();
    }
    
    @Bean
    public SecurityConfiguration securityInfo() {
        return new SecurityConfiguration("client", "secret", "realm", "api", "api_key", ApiKeyVehicle.HEADER, "api-key", ",");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        	.title(env.getProperty("swagger.api.title"))
            .description(env.getProperty("swagger.api.description"))
            .termsOfServiceUrl(env.getProperty("swagger.api.termsOfServiceUrl"))
            .license(env.getProperty("swagger.api.licence"))
            .licenseUrl(env.getProperty("swagger.api.licence.url"))
            .version(env.getProperty("swagger.api.version"))
            .build();
    }
    
}
