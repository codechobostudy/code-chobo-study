package io.codechobostudy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.header.writers.frameoptions.XFrameOptionsHeaderWriter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .headers()
                .addHeaderWriter(new XFrameOptionsHeaderWriter(XFrameOptionsHeaderWriter.XFrameOptionsMode.SAMEORIGIN))
                .cacheControl()
                .and()
            .formLogin()
                .loginPage("/signin")
                .failureUrl("/signin?error=bad_credentials")
                .and()
            .logout()
                .logoutUrl("/signout")
                .deleteCookies("JSESSIONID")
                .and()
            .authorizeRequests()
                .antMatchers("/favicon.ico", "/style/**", "/js/**", "/images/**", "/signin/**", "/connect/**", "/signup/**", "/disconnect/**")
                .permitAll()
                .antMatchers("/**")
                .authenticated()
                .and()
            .csrf()
                .disable();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
            .ignoring()
            .antMatchers("/favicon.ico", "/style/**", "/js/**", "/images/**", "/api/sample/**", "/console/**");
    }

}
