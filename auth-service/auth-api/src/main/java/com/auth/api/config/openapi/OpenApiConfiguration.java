package com.auth.api.config.openapi;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * OpenApi configuration.
 *
 * @author Anatolii Hamza
 */
@Configuration
@ConditionalOnExpression("${springdoc.api-docs.enabled}")
@OpenAPIDefinition(
        info = @Info(title = "Plant Library API",
                description = "Plant Library service open API documentation",
                version = "v0.0.1")
)
@PropertySource(value = "classpath:swagger-messages.yml", factory = SwaggerMessagesPropertySourceFactory.class)
public class OpenApiConfiguration {
}
