package io.github.evaldo.ibm.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Evaldo Fonseca da Silva",
                        email = "evaldo.fsilva2009@gmail.com",
                        url = "https://github.com/evaldovisk"
                ),
                description = "Desafio técnico submetido pela IBM para a conta do Bradesco, utilizando Java, o ecossistema Spring e PostgreSQL com procedures.",
                title = "Projeto Sistema Bancario, IBM + Bradesco.",
                version = "v1.0.0"
                ,
                termsOfService = "Terms of service"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http//localhost:8080"
                )
        }
)
public class OpenApiConfig {
}
