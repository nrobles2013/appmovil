package com.popularsafi.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception{
        httpSecurity
                //.csrf().disable()
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/tokens/user/**").permitAll()
                        .requestMatchers("/menuusuario/lista").permitAll()
                        .requestMatchers("/filedocumento/generapdf/*").permitAll()
                        .anyRequest().authenticated())
                // .oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);
                .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults() ));

        return httpSecurity.build();
    }

}
