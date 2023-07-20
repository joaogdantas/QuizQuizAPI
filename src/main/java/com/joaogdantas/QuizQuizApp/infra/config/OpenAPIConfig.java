package com.joaogdantas.QuizQuizApp.infra.config;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfig {

    @Value("${bezkoder.openapi.dev-url}")
    private String devUrl;

    @Bean
    public OpenAPI myOpenAPI() {
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in Development environment");

        Contact contact = new Contact();
        contact.setEmail("joaogdantasdev@hotmail.com");
        contact.setName("João Gabriel");
        contact.setUrl("https://www.linkedin.com/in/joaogdantas/");

        License License = new License().name("Licença - João Gabriel").url("https://www.linkedin.com/in/joaogdantas/");

        Info info = new Info()
                .title("Quiz Quiz App API")
                .version("0.0.1")
                .contact(contact)
                .description("Uma API para um app de quiz, onde poderiam ser criados quizzes para o usuário.").termsOfService("https://github.com/joaogdantas/QuizQuizAPI")
                .license(License);

        return new OpenAPI().info(info).servers(List.of(devServer));
    }
}
