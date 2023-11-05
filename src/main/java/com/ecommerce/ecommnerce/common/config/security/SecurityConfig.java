package com.ecommerce.ecommnerce.common.config.security;

import java.util.*;

import com.ecommerce.ecommnerce.common.config.security.securitycomponents.JwtAuthenticationFilter;
import com.ecommerce.ecommnerce.common.config.security.securitycomponents.JwtUserDetailService;
import com.fasterxml.jackson.databind.ObjectMapper;

//import io.swagger.v3.oas.models.security.SecurityScheme;
//import org.springdoc.core.customizers.OpenApiCustomiser;

import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;


@Configuration
@EnableWebSecurity
@PropertySource("classpath:constant/constant.properties")
public class SecurityConfig  {

    public static final List<GrantedAuthority> ROLE_USER = AuthorityUtils.createAuthorityList("ROLE_USER");

    public static final String[] URLS_THAT_DONT_NEED_AUTHENTICATION = {

            "/authentication/login", "/login",
            "/swagger-ui.html", "/swagger-ui/index.html",
            "/favicon.ico", "/swagger-ui/swagger-ui.css", "/swagger-ui/index.css",
            "/swagger-ui/swagger-initializer.js",
            "/bus/v3/api-docs/", "/add/password", "/swagger-ui/swagger-ui-standalone-preset.js",
            "/webjars/**", "/swagger-ui/swagger-ui-bundle.js", "/v3/api-docs/swagger-config", "/v3/api-docs",
            "/login", "/login.jsp", "/party/customer", "/swagger-ui/favicon-32x32.png", "/demo","/user/register","/error"
    };;

//    @Autowired
//    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Value("${settings.cors.origin}")
    private String corsOrigin ;


    @Bean
    public UserDetailsService userDetailsService() {
        return new JwtUserDetailService();
    }



    @Bean
    protected SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Enter");
        http

                .authenticationManager(authenticationManager)
                .csrf(csrf -> csrf.disable())

                .authorizeHttpRequests(auth -> auth
                        //.requestMatchers("/swagger-ui/index.html")
                        .requestMatchers(URLS_THAT_DONT_NEED_AUTHENTICATION).permitAll()
                        .anyRequest().authenticated()
                )

                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }



    @Bean
    CorsConfigurationSource corsConfigurationSource()
    {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedOriginPattern(corsOrigin);
//       configuration.addAllowedOrigin(corsOrigin);
        configuration.addAllowedHeader("Content-Type");
        configuration.addAllowedHeader("Authorization");
        configuration.addAllowedHeader("X-Requested-With");
        configuration.addAllowedHeader("authorization");
        configuration.addAllowedHeader("multipart/form-data");
        configuration.setAllowCredentials(false);
        configuration.setAllowedMethods(Arrays.asList("GET","POST", HttpMethod.OPTIONS.toString()));
        configuration.setMaxAge((long) 86400);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




    @Bean
    public ObjectMapper getObjectMapper(){
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        return objectMapper;
    }

    @Bean
    public BasicTextEncryptor getBasicTextEncryptor(){
        return new BasicTextEncryptor();
    }


}


