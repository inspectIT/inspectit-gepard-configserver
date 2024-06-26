/* (C) 2024 */
package rocks.inspectit.gepard.configserver.agent;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.Instant;
import org.junit.jupiter.api.Test;

public class AgentTest {

  @Test
  public void testNoArgs() {
    // Act
    Agent agent = new Agent();
    // Assert
    assertNotNull(agent);
  }

  @Test
  public void testAllArgs() {
    // Act
    Agent agent = new Agent("agentName", 1L, "otelVersion", "123L", Instant.now(), "javaVersion");
    // Assert
    assertNotNull(agent);
  }

  @Test
  public void testEquast() {
    // Act
    Agent agent1 = new Agent();
    Agent agent2 = new Agent();

    // Assert
    assertNotNull(agent1);
  }

  @Test
  public void testHashCode() {
    // Act
    Agent agent = new Agent();
    // Assert agent.hashCode() is an int
    assertThat(agent.hashCode()).isInstanceOf(Integer.class);
  }

  @Test
  public void testToString() {
    // Act
    Agent agent = new Agent("agentName", 1L, "otelVersion", "123L", Instant.now(), "javaVersion");

    assertThat(agent.toString()).contains("agentName");
  }
}
