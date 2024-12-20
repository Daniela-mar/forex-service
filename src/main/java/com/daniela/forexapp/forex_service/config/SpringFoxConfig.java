package com.daniela.forexapp.forex_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
public class SpringFoxConfig {
  @Bean
  public Docket api() {
    return new Docket(DocumentationType.SWAGGER_2)
      .select()
      .apis(RequestHandlerSelectors.basePackage("com.daniela.forexapp.forex-service"))
      .paths(PathSelectors.regex("/."))
      .build().apiInfo(apiInfoMetaData());
  }

  private ApiInfo apiInfoMetaData() {

    return new ApiInfoBuilder().title("Forex")
      .description("Forex App Using CurrencyLayer.com")
      .build();
  }
}
