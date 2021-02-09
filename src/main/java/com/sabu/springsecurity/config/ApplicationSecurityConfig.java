package com.sabu.springsecurity.config;


import com.sabu.springsecurity.security.AuthenticationService;
import com.sabu.springsecurity.security.JwtAuthenticationFilter;
import com.sabu.springsecurity.security.JwtTokenVerifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration for Spring security
 */
@Configuration
@EnableWebSecurity // It allows Spring to find (it's a @Configuration and, therefore, @Component)
// and automatically apply the class to the global WebSecurity.
// WebSecurityConfigurerAdapter- It allows configuring things that impact all of web security.
// WebSecurityConfigurerAdapter is a convenience class that allows customization to both WebSecurity and HttpSecurity.
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationService authenticationService;

    public ApplicationSecurityConfig(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        // configure authentication manager
//        auth.userDetailsService(authenticationService).passwordEncoder(passwordEncoder());
//        // auth.userDetailsService() will initiate the DaoAuthenticationProvider interface
//        // using our implementation of UserDetailsService interface and register it in Authentication manager.
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // Enable CORS and disable CSRF
//        http = http.cors().and().csrf().disable();

//         Set session management to stateless
        http = http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and();

        // Set unauthorized requests exception handler
//        http = http
//                .exceptionHandling()
//                .authenticationEntryPoint(
//                        (request, response, ex) -> {
//                            response.sendError(
//                                    HttpServletResponse.SC_UNAUTHORIZED,
//                                    ex.getMessage()
//                            );
//                        }
//                )
//                .and();

        // Set permissions on endpoints
        http.csrf().disable()
                .addFilter(new JwtAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), JwtAuthenticationFilter.class)// Add JWT verifier filter after authentication filter
                .authorizeRequests()
                // Our public endpoints
                .antMatchers("/api/public/**").permitAll()
//                .antMatchers("/user/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/author/search").permitAll()
//                .antMatchers(HttpMethod.GET, "/api/book/**").permitAll()
//                .antMatchers(HttpMethod.POST, "/api/book/search").permitAll()
                // Our private endpoints
                .anyRequest().authenticated()
                .and()
                .httpBasic();

        // Add JWT token filter
//        http.addFilterBefore(
//                jwtTokenFilter,
//                UsernamePasswordAuthenticationFilter.class
//        );
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

//    // Used by spring security if CORS is enabled.
//    @Bean
//    public CorsFilter corsFilter() {
//        UrlBasedCorsConfigurationSource source =
//                new UrlBasedCorsConfigurationSource();
//        CorsConfiguration config = new CorsConfiguration();
//        config.setAllowCredentials(true);
//        config.addAllowedOrigin("*");
//        config.addAllowedHeader("*");
//        config.addAllowedMethod("*");
//        source.registerCorsConfiguration("/**", config);
//        return new CorsFilter(source);
//    }
}
