package com.ohgiraffers.goonthatbackend.metamate.member.config;

import com.ohgiraffers.goonthatbackend.metamate.member.handler.CustomLoginSuccessHandler;
import com.ohgiraffers.goonthatbackend.metamate.member.service.MetaUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@ComponentScan("com.ohgiraffers.goonthatbackend")
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MetaUserDetailService metaUserDetailService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**")
                .antMatchers("/css/bootstrap.min.css");

        web.ignoring() .requestMatchers(PathRequest.toStaticResources().atCommonLocations());

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/","/login", "/join").permitAll()
                .mvcMatchers(
                        "/",
                        "/join",
                        "/css/**",
                        "/scripts/**",
                        "/plugin/**",
                        "/fonts/**",
                        "/docs/**",
                        "/webjars/**",
                        "/cover.css",
                        "/css/bootstrap.min.css"
                )
                .permitAll()
                .antMatchers("/admin").hasRole("ADMIN")
                .antMatchers("/myinfo").authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .successHandler(customLoginSuccessHandler())
                .failureForwardUrl("/fail")
                .and()
                .logout()
                .logoutUrl("/logout");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CustomLoginSuccessHandler customLoginSuccessHandler() {
        return new CustomLoginSuccessHandler();
    }

    @Bean
    public CustomAuthenticationProvider CustomAuthenticationProvider() {
        return new CustomAuthenticationProvider(metaUserDetailService, bCryptPasswordEncoder());
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) {
        authenticationManagerBuilder.authenticationProvider(CustomAuthenticationProvider());
    }
}