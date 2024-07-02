/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.controller;

import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocks.inspectit.gepard.configserver.connection.service.UIConnectionService;

/**
 * Controller for handling UI connection requests.
 * Holds the GET endpoints for handling connection requests from the UI.
 */
@RestController
@RequestMapping("/api/v1/connections")
@RequiredArgsConstructor
public class UIConnectionController {

  private final UIConnectionService connectionService;

  @GetMapping
  public ResponseEntity<?> getConnections() {
    return ResponseEntity.ok(connectionService.getConnections());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getConnection(@PathVariable UUID id) {
    return ResponseEntity.ok(connectionService.getConnection(id));
  }
}
