/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import rocks.inspectit.gepard.configserver.connection.service.UIConnectionService;

@ExtendWith(MockitoExtension.class)
public class UIConnectionControllerTest {

  @InjectMocks private UIConnectionController controller;

  @Mock private UIConnectionService connectionService;

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
