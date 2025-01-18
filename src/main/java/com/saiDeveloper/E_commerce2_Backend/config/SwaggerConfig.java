package com.saiDeveloper.E_commerce2_Backend.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
    info = @Info(
        title = "E-commerce API",
        version = "1.0.0",
        description = "E-commerce API",
        contact = @Contact(
            name = "Sai Developer",
            url = "https://github.com/saiDeveloper",
            email = "Fw3Qs@example.com"
        ),
        license = @License(
            name = "Apache 2.0",
            url = "https://www.apache.org/licenses/LICENSE-2.0"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080",
            description = "Local Server"
        ),
            @Server(
            url = "https://sai-developer-ecommerce.onrender.com",
            description = "Production Server"
        )
    },
        tags = {
            @io.swagger.v3.oas.annotations.tags.Tag(name = "Authentication", description = "Authentication endpoints"),
            @io.swagger.v3.oas.annotations.tags.Tag(name = "User", description = "User endpoints"),
            @io.swagger.v3.oas.annotations.tags.Tag(name = "Product", description = "Product endpoints"),
            @io.swagger.v3.oas.annotations.tags.Tag(name = "Cart", description = "Cart endpoints"),
                @Tag(name = "Order", description = "Order endpoints"),
        }

)
@Configuration
public class SwaggerConfig {

}
