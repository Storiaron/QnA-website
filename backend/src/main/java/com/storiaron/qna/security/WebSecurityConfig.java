package com.storiaron.qna.security;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.ExceptionTranslationFilter;

@Configuration
@EnableWebSecurity(debug = false)
public class WebSecurityConfig {

    private final UserDetailsService userDetailsService;

    @Autowired
    public WebSecurityConfig(UserDetailsService userDetailsService) {

        this.userDetailsService = userDetailsService;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/api/content/post/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/user/register").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/content/post/newest").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/api/content/comment/newest").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/content/post").hasAnyRole("User", "Admin")
                        .requestMatchers(HttpMethod.POST, "/api/content/comment").hasAnyRole("User", "Admin")
                        .anyRequest()
                        .authenticated()
                )
                .addFilterAfter(customUsernameAndPasswordAuthenticationFilter(), ExceptionTranslationFilter.class)
                .addFilterAfter(bearerTokenAuthenticatingFilter(), ExceptionTranslationFilter.class)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        ;
        return http.build();
    }
    @Bean
    public AuthenticationManager customAuthenticationManager() {
        return new CustomAuthenticationManager(userDetailsService, passwordEncoder());
    }

    @Bean
    public CustomUsernameAndPasswordAuthenticationFilter customUsernameAndPasswordAuthenticationFilter() {
        return new CustomUsernameAndPasswordAuthenticationFilter(customAuthenticationManager());
    }

    @Bean
    public BearerTokenAuthenticatingFilter bearerTokenAuthenticatingFilter() {
        return new BearerTokenAuthenticatingFilter(tokenUtil());
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public TokenUtil tokenUtil() {
        return new TokenUtil(userDetailsService);
    }
}
