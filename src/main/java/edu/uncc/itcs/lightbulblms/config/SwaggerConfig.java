package edu.uncc.itcs.lightbulblms.config;

import edu.uncc.itcs.lightbulblms.controller.annotation.AdminOperation;
import edu.uncc.itcs.lightbulblms.controller.annotation.StudentOperation;
import edu.uncc.itcs.lightbulblms.controller.annotation.TeacherOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
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
    public Docket adminAPIs() {
        return new Docket(DocumentationType.OAS_30)
                .ignoredParameterTypes(JwtAuthenticationToken.class)
                .groupName("Admin")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(AdminOperation.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(SECURITY_SCHEMES)
                .securityContexts(AUTHENTICATED_API)
                ;
    }

    @Bean
    public Docket teacherAPIs() {
        return new Docket(DocumentationType.OAS_30)
                .ignoredParameterTypes(JwtAuthenticationToken.class)
                .groupName("Teacher")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(TeacherOperation.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .securitySchemes(SECURITY_SCHEMES)
                .securityContexts(AUTHENTICATED_API)
                ;
    }

    @Bean
    public Docket studentAPIs() {
        return new Docket(DocumentationType.OAS_30)
                .ignoredParameterTypes(JwtAuthenticationToken.class)
                .groupName("Student")
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(StudentOperation.class))
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
