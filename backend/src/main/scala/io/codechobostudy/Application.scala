package io.codechobostudy

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.builder.SpringApplicationBuilder
import org.springframework.boot.context.web.SpringBootServletInitializer
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.web.WebApplicationInitializer

object Application extends App {
  SpringApplication.run(classOf[Application])
}

@Configuration
@EnableAutoConfiguration
@ComponentScan
class Application extends SpringBootServletInitializer with WebApplicationInitializer {

  protected override def configure(application: SpringApplicationBuilder): SpringApplicationBuilder = {
    application.sources(classOf[Application])
  }

  @Bean
  def objectMapper() : ObjectMapper = {
    return new ObjectMapper()
  }
}