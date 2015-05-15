package io.codechobostudy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
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
            .antMatchers("/", "/index", "/favicon.ico", "/style/**", "/js/**", "/images/**", "/api/sample/**");
    }

}
