package org.myworkspace.LibraryManagement.Configurations;

import org.myworkspace.LibraryManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Autowired
    private UserService userService;

    @Value("${consumer.authority}")
    private String consumerAuthority;

    @Value("${admin.authority}")
    private String adminAuthority;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(CommonConfig.getEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/user/addConsumer/**").permitAll()
                .requestMatchers("/user/addAdmin/**").permitAll() //assuming only admin know this endpoint
                .requestMatchers("/user/filter/**").hasAnyAuthority(adminAuthority, consumerAuthority)
                .requestMatchers("/transactions/createTxn/**").hasAuthority(adminAuthority)
                .requestMatchers("/transactions/return/**").hasAuthority(adminAuthority)
                .requestMatchers("/books/addBook/**").hasAuthority(adminAuthority)
                .requestMatchers("/books/filter/**").hasAnyAuthority(adminAuthority, consumerAuthority)
                .anyRequest().authenticated()
        ).formLogin(Customizer.withDefaults()).httpBasic(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

}
