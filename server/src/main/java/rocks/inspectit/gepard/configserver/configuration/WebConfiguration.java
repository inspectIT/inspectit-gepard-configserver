/* (C) 2024 */
package rocks.inspectit.gepard.configserver.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {

  @Override
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
    registry.addResourceHandler("/ui/**").addResourceLocations("classpath:/static/");
  }

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
