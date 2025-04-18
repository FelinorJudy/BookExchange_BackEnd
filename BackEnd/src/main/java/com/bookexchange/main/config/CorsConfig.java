package com.bookexchange.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // Permetti le richieste CORS dal frontend su http://localhost:4100
        registry.addMapping("/**")  // Configura per tutte le rotte
                .allowedOrigins("http://localhost:4200")  // Frontend
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Metodi consentiti
                .allowedHeaders("*")  // Permetti qualsiasi header
                .allowCredentials(true);  // Consenti invio di credenziali come cookies
    }
}