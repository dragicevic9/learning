package com.eazybytes.accounts;

import com.eazybytes.accounts.dto.AccountsContactInfoDto;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
        info = @Info(
                title = "Accounts microservice REST API Documentation",
                description = "EazyBank Account microservice REST API Documentation",
                version = "v1",
                contact = @Contact(
                        name = "Marko Dragicevic",
                        email = "marko.dragicevic@levi9.com",
                        url = "levi9.com"
                ),
                license = @License(
                        name = "Apache 2.0",
                        url = "levi9.com"
                )
        ),
        externalDocs = @ExternalDocumentation(
                description = "EazyBank Account microservice REST API Documentation"
        )
)
@EnableConfigurationProperties(value = {AccountsContactInfoDto.class})
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }

}
