package io.codechobostudy.config;

import org.h2.server.web.WebServlet;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    @Bean(destroyMethod = "shutdown")
    public DataSource userDataSource() {
        return new EmbeddedDatabaseBuilder().
            setType(EmbeddedDatabaseType.H2).
            setName("test").
            addScript("classpath:schema/user.sql").
            build();
    }

    @Bean
    @Profile("dev")
    public ServletRegistrationBean h2servletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new WebServlet());
        registration.addUrlMappings("/console/*");
        return registration;
    }

}
