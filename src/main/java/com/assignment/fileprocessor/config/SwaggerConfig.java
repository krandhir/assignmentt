package com.assignment.fileprocessor.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;

/**
 * The type Swagger config.
 */
public class SwaggerConfig {

  /**
   * Custom open api open api.
   *
   * @return the open api
   */
  @Bean
  public OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(new Info()
            .title("File processor API")
            .version("1.0")
            .description("API documentation for the File processor application"));
  }
}
