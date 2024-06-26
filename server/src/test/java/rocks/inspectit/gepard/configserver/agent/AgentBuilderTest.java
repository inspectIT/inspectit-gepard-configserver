/* (C) 2024 */
package rocks.inspectit.gepard.configserver.agent;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Instant;
import org.junit.jupiter.api.Test;

public class AgentBuilderTest {

  @Test
  public void testBuildAgent() {
    // Act
    Agent agent =
        Agent.builder()
            .serviceName("agentName")
            .gepardVersion("gepardVersion")
            .otelVersion("otelVersion")
            .pid(123L)
            .startTime(Instant.now())
            .javaVersion("javaVersion")
            .build();
    // Assert
    assertNotNull(agent);
  }

  @Test
  public void testBuildAgentWithNullValues() {
    // Assert
    assertThrows(NullPointerException.class, () -> Agent.builder().build());
  }
}
