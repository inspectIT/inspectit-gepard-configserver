/* (C) 2024 */
package rocks.inspectit.gepard.configserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Web configuration for the application.
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  /**
   * Adds CORS mappings to the registry.
   * Allow all origins and methods.
   * @param registry
   */
  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
  }

  /**
   * Adds resource handlers to the registry.
   * All requests to /ui/** will serve the static folder.
   * @param registry the resource handler registry
   */
  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/ui/**").addResourceLocations("classpath:/static/");
  }

  /**
    * Adds view controllers to the registry.
    * Redirects all requests to /ui, /, /ui/connections, /ui/connections/agent-settings to their respective index.html files.
    * If new endpoints are added, they should be added here.
    * @param registry the view controller registry
    */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry
        .addRedirectViewController("/ui", "/ui/")
        .setKeepQueryParams(true)
        .setStatusCode(HttpStatus.PERMANENT_REDIRECT);
    registry
        .addRedirectViewController("/", "/ui/")
        .setKeepQueryParams(true)
        .setStatusCode(HttpStatus.PERMANENT_REDIRECT);
    registry
        .addRedirectViewController("/ui/connections", "/ui/connections/")
        .setKeepQueryParams(true)
        .setStatusCode(HttpStatus.PERMANENT_REDIRECT);
    registry
        .addRedirectViewController(
            "/ui/connections/agent-settings", "/ui/connections/agent-settings/")
        .setKeepQueryParams(true)
        .setStatusCode(HttpStatus.PERMANENT_REDIRECT);
  }
}
