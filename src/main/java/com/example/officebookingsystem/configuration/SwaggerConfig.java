package com.example.officebookingsystem.configuration;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private static final String SECURITY_REFERENCE = "Token Access";
    private static final String AUTHORIZATION = "Authorization";
    private static final String AUTHORIZATION_DESCRIPTION = "JWT Token is required to access the API.";
    private static final String AUTHORIZATION_SCOPE = "Unlimited";
    private static final String CONTACT_EMAIL = "mgpmichael@gmail.com (+62 8127 6522)";
    private static final String CONTACT_URL = "";
    private static final String CONTACT_NAME = ": Michael MGP Simatupang";
    private static final String API_TITLE = "API Office Booking System";
    private static final String API_DESCRIPTION = "API Description";
    private static final String TERM_OF_SERVICE = "";
    private static final String API_VERSION = "1.0";
    private static final String LICENSE = "Apache License 2.0";
    private static final String LICENSE_URL = "https://www.apache.org/licenses/LICENSE-2.0";
    private static final String SECURE_PATH = "/*/.*";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).forCodeGeneration(true)
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.officebookingsystem.controller"))
                .paths(PathSelectors.regex(SECURE_PATH))
                .build();
    }

    public ApiInfo apiInfo() {
        return new ApiInfo(API_TITLE, API_DESCRIPTION, API_VERSION, TERM_OF_SERVICE, contact(), LICENSE, LICENSE_URL,
                Collections.emptyList());
    }

    private Contact contact() {
        return new Contact(CONTACT_NAME, CONTACT_URL, CONTACT_EMAIL);
    }

    private ApiKey apiKey() {
        return new ApiKey(SECURITY_REFERENCE, AUTHORIZATION,
                io.swagger.v3.oas.models.security.SecurityScheme.In.HEADER.name());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(securityReference())
                .build();
    }

    private List<SecurityReference> securityReference() {
        AuthorizationScope[] authorizationScopes = {
                new AuthorizationScope(AUTHORIZATION_SCOPE, AUTHORIZATION_DESCRIPTION) };
        return Collections.singletonList(new SecurityReference(SECURITY_REFERENCE, authorizationScopes));
    }

}
