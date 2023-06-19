package com.bosonit.formacion.block14springsecurity.security.config;

import com.bosonit.formacion.block14springsecurity.security.filter.JwtAuthorizationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig{
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";

    /**Configuración para autorización de peticiones de cliente. Filtro según el rol (admin / user).
     *
     * @param http
     * @return http.build()
     * @throws Exception
     */
    @Bean
    public SecurityFilterChain web(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .addFilterAfter(new JwtAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST,"/login").permitAll()
                        .requestMatchers(HttpMethod.GET).hasAnyRole(ROLE_ADMIN, ROLE_USER)
                        .requestMatchers(HttpMethod.POST).hasRole(ROLE_ADMIN)
                        .requestMatchers(HttpMethod.PUT).hasRole(ROLE_ADMIN)
                        .requestMatchers(HttpMethod.DELETE).hasRole(ROLE_ADMIN)
                        .anyRequest().authenticated());

        return http.build();
    }

}
