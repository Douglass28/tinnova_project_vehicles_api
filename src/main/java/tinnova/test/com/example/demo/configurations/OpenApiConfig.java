package tinnova.test.com.example.demo.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import jakarta.servlet.ServletContext;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI(ServletContext servletContext) {
        final String securitySchemeName = "Authorization";
        return new OpenAPI()
                .addServersItem(new Server().url(servletContext.getContextPath()))
                .components(
                        new Components()
                                .addSecuritySchemes(
                                        securitySchemeName,
                                        new SecurityScheme()
                                                .name(securitySchemeName)
                                                .type(SecurityScheme.Type.HTTP)
                                                .scheme("bearer")
                                                .bearerFormat("JWT")))
                .info(
                        new Info()
                                .title("Vehicles - Tinnova")
                                .version("1.0.0")
                                .description(
                                        "This project is the Vehicles Tinnova, a project that aims to provide a base for new projects in Tinnova")
                                .termsOfService("http://swagger.io/terms/")
                                .contact(
                                        new Contact().name("Tinnova").url("https://www.tinnova.com.br/"))
                                .license(new License().name("Apache 2.0").url("http://springdoc.org")));
    }

}
