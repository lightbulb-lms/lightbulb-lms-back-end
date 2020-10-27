package edu.uncc.itcs.lightbulblms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.HttpAuthenticationScheme;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;

@Configuration
public class SwaggerConfig {
    @Bean
    public Docket api() {

        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(Collections.singletonList(
                        new HttpAuthenticationScheme("Authenticated API",
                                "Paste in Okta Access Token JWT here",
                                "http", "bearer", "jwt", Collections.emptyList())))
                .securityContexts(Collections.singletonList(SecurityContext.builder()
                        .securityReferences(
                                Collections.singletonList(
                                    new SecurityReference("Authenticated API", new AuthorizationScope[0])))
                        .operationSelector(operationContext -> true)
                        .build()
                ))
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Lightbulb LMS Backend")
                .description("Back end for the Lightbulb LMS project")
                .build();
    }

}
