package io.codechobostudy.config

import org.springframework.beans.factory.annotation.{Autowired, Value}
import org.springframework.context.annotation.{Bean, ComponentScan, Configuration}
import org.springframework.core.env.Environment
import org.springframework.web.servlet.config.annotation.{ResourceHandlerRegistry, ViewControllerRegistry, WebMvcConfigurerAdapter}
import org.springframework.web.servlet.resource.{AppCacheManifestTransformer, ResourceUrlEncodingFilter, VersionResourceResolver}

@Configuration
@ComponentScan
class WebConfig extends WebMvcConfigurerAdapter {

  private var environment: Environment = _

  @Value("${app.version:}")
  private var appVersion: String = _


  override def addViewControllers(registry: ViewControllerRegistry) {
    registry.addViewController("/").setViewName("index")
  }

  @Bean
  def resourceUrlEncodingFilter: ResourceUrlEncodingFilter = {
    new ResourceUrlEncodingFilter
  }

  override def addResourceHandlers(registry: ResourceHandlerRegistry ) {

    val isDevMode = environment.acceptsProfiles("dev")
    val pathPatterns = "/**"
    var cachePeriod: java.lang.Integer = null
    var useResourceCache = false

    val locations = "classpath:static/"

    if (isDevMode) {
      cachePeriod = 0
      useResourceCache = true
    }

    val appCacheTransformer = new AppCacheManifestTransformer
    val versionResolver = new VersionResourceResolver()
      //.addFixedVersionStrategy(getApplicationVersion, "/**/*.js", "/**/*.map")
      .addContentVersionStrategy("/**")

    registry.addResourceHandler(pathPatterns)
      .addResourceLocations(locations)
      .setCachePeriod(cachePeriod)
      .resourceChain(useResourceCache)
      .addResolver(versionResolver)
      .addTransformer(appCacheTransformer)
  }

  def getApplicationVersion : String = {
    return if (environment.acceptsProfiles("dev")) "dev" else appVersion
  }

  @Autowired
  def setEnvironment(environment: Environment) {
    this.environment = environment
  }
}