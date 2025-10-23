package com.abpro.proyecto.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración principal de Spring MVC
 *
 * @EnableWebMvc: Activa la configuración de Spring MVC
 * @ComponentScan: Escanea el paquete base para encontrar componentes (@Controller, @Service, etc.)
 * @Configuration: Indica que esta clase contiene configuración de Spring
 *
 * @author Equipo de Desarrollo ABPRO
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.abpro.proyecto")
public class WebConfig implements WebMvcConfigurer {

    /**
     * Configura el validador de beans usando Hibernate Validator
     * Esto permite usar anotaciones como @NotNull, @NotEmpty, @Min, @Max, etc.
     */
    @Bean
    public LocalValidatorFactoryBean validator() {
        return new LocalValidatorFactoryBean();
    }

    /**
     * Habilita la validación de métodos en Spring
     * Permite usar @Valid en parámetros de métodos
     */
    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        MethodValidationPostProcessor processor = new MethodValidationPostProcessor();
        processor.setValidator(validator());
        return processor;
    }
}
