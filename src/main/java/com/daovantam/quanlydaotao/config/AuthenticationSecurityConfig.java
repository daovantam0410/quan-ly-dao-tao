package com.daovantam.quanlydaotao.config;


import com.daovantam.quanlydaotao.security.AuthenticationFilter;
import com.daovantam.quanlydaotao.security.TokenAuthenticator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class AuthenticationSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private TokenAuthenticator tokenAuthenticator;
    @Autowired
    private ObjectMapper objectMapper;

    public AuthenticationSecurityConfig() {
        super(true);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().exceptionHandling()
                .and()
                .anonymous()
                .and()
                .servletApi()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**")
                .permitAll()
                .antMatchers("/")
                .permitAll()
                .antMatchers("/favicon.ico")
                .permitAll()
                .antMatchers(HttpMethod.POST,"/admin/**")
                .permitAll()
                .antMatchers("/admin/**")
                .permitAll()
                .and()
                .headers()
                .cacheControl();

        http.authorizeRequests().anyRequest().hasRole("ADMIN");

        http.addFilterBefore(new AuthenticationFilter(tokenAuthenticator, objectMapper), UsernamePasswordAuthenticationFilter.class);
    }

    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources/**",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**");
    }
}
