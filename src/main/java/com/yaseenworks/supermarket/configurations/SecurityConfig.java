package com.yaseenworks.supermarket.configurations;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf(AbstractHttpConfigurer::disable /*Original: (conf) -> conf.disable()*/)
                .authorizeHttpRequests((matchers) -> {
                    matchers.requestMatchers("/api/v1/auth/**").permitAll().anyRequest().authenticated();
                })
//                .securityMatchers((matchers) -> matchers
//                .requestMatchers("**")).authorizeHttpRequests((authorize) -> authorize
//                .anyRequest().authenticated() //Lambda Expression: AbstractRequestMatcherRegistry::anyRequest
//        )
                .sessionManagement((managementConf) -> managementConf.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
//        httpSecurity. authorizeHttpRequests((c) -> {
//c.anyRequest().authenticated().and().httpBasic()
//        });
        return httpSecurity.build();
    }
}
