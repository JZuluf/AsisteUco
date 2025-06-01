package co.edu.uco.asisteuco.infrastructure.configuration; // Asegúrate de que este sea el paquete correcto

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
// import org.springframework.http.HttpMethod; // No lo necesitamos para anyRequest().permitAll()
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration      
@EnableWebSecurity  
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .anyRequest().permitAll() 
            )
            .csrf(AbstractHttpConfigurer::disable); // Mantener CSRF deshabilitado

        // .oauth2ResourceServer(oauth2 -> oauth2.jwt(withDefaults())); // Mantenlo comentado por ahora

        return http.build();
    }
}