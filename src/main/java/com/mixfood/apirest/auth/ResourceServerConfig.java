package com.mixfood.apirest.auth;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter
{
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers(HttpMethod.GET,
                "/api/followers",
                "/api/categories/**",
                "/api/tags/**",
                "/api/rankings/**",
                "/api/uploads/**",
                "/api/recipes/**",
                //Temporal
                "/api/followers/**",
                //Temporal
                "/api/ingredients/**",
                "/api/users/**",
                //Tempora
                "/api/users/**").permitAll()
                .antMatchers(HttpMethod.POST,"/api/users/register","/api/ingredients/**","/api/tags/**","/api/categories/**","/api/images/**","/api/users/**").permitAll()
                .antMatchers(HttpMethod.DELETE,"/api/users/register","/api/ingredients/**","/api/tags/**","/api/images/**","/api/users/**").permitAll()
                .antMatchers(HttpMethod.PUT,"/api/users/register","/api/ingredients/**","/api/tags/**","/api/users/**").permitAll().anyRequest().authenticated();
//                .antMatchers(HttpMethod.GET,"/api/users/{id}").hasRole("ADMIN")
//                .antMatchers(HttpMethod.POST,"/api/recipes/").hasRole("USER")

    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource ()
    {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList("http://localhost:4200"));
        config.setAllowedMethods(Arrays.asList("GET","POST","PUT","DELETE","OPTIONS"));
        config.setAllowCredentials(true);
        config.setAllowedHeaders(Arrays.asList("Content-type","Authorization"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",config);
        return source;
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilter()
    {
        FilterRegistrationBean bean = new FilterRegistrationBean<CorsFilter>(new CorsFilter(corsConfigurationSource()));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
