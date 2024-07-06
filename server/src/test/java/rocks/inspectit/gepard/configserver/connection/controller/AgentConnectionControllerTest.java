/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionSuccessfulResponse;
import rocks.inspectit.gepard.configserver.connection.service.AgentConnectionService;

@ExtendWith(MockitoExtension.class)
public class AgentConnectionControllerTest {
  @InjectMocks AgentConnectionController controller;

  @Mock AgentConnectionService agentConnectionService;

  private CreateConnectionSuccessfulResponse createConnectionSuccessfulResponse;

  @BeforeEach
  public void setUp() {
    createConnectionSuccessfulResponse =
        new CreateConnectionSuccessfulResponse(
            new UUID(0, 0),
            "agentName",
            "gepardVersion",
            "otelVersion",
            123L,
            1719394483600L,
            "javaVersion",
            "connect",
            true);
  }

  @Test
  public void testConnectValidPayload() {
    // Arrange
    CreateConnectionRequest createConnectionRequest =
        new CreateConnectionRequest(
            "agentName", "gepardVersion", "otelVersion", 123L, 1719394483600L, "javaVersion");

    when(agentConnectionService.handleConnectRequest(createConnectionRequest))
        .thenReturn(createConnectionSuccessfulResponse);

    // Act
    ResponseEntity<?> result = controller.connect(createConnectionRequest);

    // Assert
    assertEquals(ResponseEntity.ok().body(createConnectionSuccessfulResponse), result);
  }
}
