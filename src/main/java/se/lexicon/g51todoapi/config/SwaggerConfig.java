package se.lexicon.g51todoapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "TODO IT API", version = "0.1", description = "API Information"))
public class SwaggerConfig {
}
