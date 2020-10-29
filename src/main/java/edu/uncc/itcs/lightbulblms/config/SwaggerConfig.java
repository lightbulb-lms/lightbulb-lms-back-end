package edu.uncc.itcs.lightbulblms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Collections;
import java.util.List;

@Configuration
public class SwaggerConfig {

    public static final List<SecurityContext> AUTHENTICATED_API = Collections.singletonList(SecurityContext.builder()
            .securityReferences(
                    Collections.singletonList(
                            new SecurityReference("Authenticated API", new AuthorizationScope[0])))
            .operationSelector(operationContext -> true)
            .build()
    );

    public static final List<SecurityScheme> SECURITY_SCHEMES = Collections.singletonList(
            new HttpAuthenticationScheme("Authenticated API",
                    "Paste in Okta Access Token JWT here",
                    "http", "bearer", "jwt", Collections.emptyList()));

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.OAS_30)
                .select()
                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(SECURITY_SCHEMES)
                .securityContexts(AUTHENTICATED_API)
                ;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Lightbulb LMS Backend")
                .description("Back end for the Lightbulb LMS project")
                .build();
    }

}
