package io.codechobostudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;


@Configuration
public class DataSourceConfig {

    @Bean(destroyMethod = "shutdown")
    public DataSource userDataSource() {
        return new EmbeddedDatabaseBuilder().
            setType(EmbeddedDatabaseType.H2).
            addScript("classpath:schema/user.sql").
            build();
    }

}
