package com.lnlib.springboot.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * If using Spring Boot, there is no need to use @EnableSwagger2,
 * nor to configure WebMvcConfigurer, nor to enable @EnableWebMvc
 * <p>
 * Note: springdoc-openapi replaces Swagger2
 */
@Configuration
public class SwaggerConfig
{
//    /**
//     * Swagger2 config.
//     * It is not required with springdoc-openapi
//     */
//    @Bean
//    public Docket api()
//    {
//        return new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .apis(RequestHandlerSelectors.any())
//                .paths(PathSelectors.any())
//                .build()
//                .apiInfo(getApiInfo());
//    }

//    /**
//     * Fills the header
//     *
//     * Swagger2 config
//     */
//    private ApiInfo getApiInfo()
//    {
//        return new ApiInfo("Sample application",
//                "It gives basic examples",
//                "1.0.0",
//                "http://terms.of.services",
//                new Contact("ln", "http://github.com/lno06", "ln@ln.com"),
//                "license",
//                "http://license",
//                List.of());
//    }

    /**
     * Fills the header
     * <p>
     * springdoc-open api config
     */
    @Bean
    public OpenAPI openAPI()
    {
        return new OpenAPI()
                .info(new Info().title("Sample application")
                        .description("It gives basic examples")
                        .version("1.0.0")
                        .license(new License().name("license").url("http://license"))
                        .contact(new Contact().name("ln").url("http://github.com/lno06").email("ln@ln.com")));
    }
}
