/* (C) 2024 */
package rocks.inspectit.gepard.configserver;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rocks.inspectit.gepard.configserver.connection.model.ConnectionDtoMapper;

@Configuration
public class ApplicationConfiguration {

  @Bean
  public ConnectionDtoMapper connectionDtoMapper() {
    return new ConnectionDtoMapper();
  }
}
