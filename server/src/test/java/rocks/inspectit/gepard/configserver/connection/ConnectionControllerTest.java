/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection;

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

@ExtendWith(MockitoExtension.class)
public class ConnectionControllerTest {
  @InjectMocks ConnectionController controller;

  @Mock ConnectionService connectionService;

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

    when(connectionService.handleConnectRequest(createConnectionRequest))
        .thenReturn(createConnectionSuccessfulResponse);

    // Act
    ResponseEntity<?> result = controller.connect(createConnectionRequest);

    // Assert
    assertEquals(ResponseEntity.ok().body(createConnectionSuccessfulResponse), result);
  }

  @Test
  public void testConnectHeaderPayload() {
    // Arrange
    CreateConnectionSuccessfulResponse createConnectionSuccessfulResponse =
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

    when(connectionService.handleConnectRequestFromHeaders(
            "agentName", "gepardVersion", "otelVersion", 123L, 1719394483600L, "javaVersion"))
        .thenReturn(createConnectionSuccessfulResponse);

    // Act
    ResponseEntity<?> result =
        controller.connect(
            "agentName", "gepardVersion", "otelVersion", 123L, 1719394483600L, "javaVersion");

    // Assert
    assertEquals(ResponseEntity.ok().body(createConnectionSuccessfulResponse), result);
  }

  @Test
  public void testGetConnections() {
    // Arrange
    when(connectionService.getConnections()).thenReturn(null);

    // Act
    ResponseEntity<?> result = controller.getConnections();

    // Assert
    assertEquals(ResponseEntity.ok().body(null), result);
  }

  @Test
  public void testGetConnection() {
    // Arrange
    UUID id = new UUID(0, 0);
    when(connectionService.getConnection(id)).thenReturn(null);

    // Act
    ResponseEntity<?> result = controller.getConnection(id);

    // Assert
    assertEquals(ResponseEntity.ok().body(null), result);
  }
}
