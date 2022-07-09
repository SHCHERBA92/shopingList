package com.example.shopinglist.config;

import com.example.shopinglist.auth_model.CustomUserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomUserDetailsServiceImpl customUserDetailsService;

    public SecurityConfig(CustomUserDetailsServiceImpl customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .anyRequest()
                    .authenticated()
                .and()
                    .formLogin().loginPage("/login").permitAll()
                .and()
                    .logout().permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(customUserDetailsService).passwordEncoder(customPasswordEncoder());
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(
//                User.builder()
//                        .username("kirill")
//                        .password(passwordEncoder().encode("123"))
//                        .roles("ADMIN")
//                        .build(),
//                User.builder()
//                        .username("user")
//                        .password(passwordEncoder().encode("123"))
//                        .roles("USER")
//                        .build()
//        );
//    }

    @Bean
    protected PasswordEncoder customPasswordEncoder(){
        return new BCryptPasswordEncoder(12);
    }
}
