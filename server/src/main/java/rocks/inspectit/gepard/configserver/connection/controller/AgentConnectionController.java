/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;
import rocks.inspectit.gepard.configserver.connection.service.AgentConnectionService;

@RestController
@RequestMapping("/api/v1/connections")
@RequiredArgsConstructor
public class AgentConnectionController {

  private final AgentConnectionService connectionService;

  @PostMapping
  public ResponseEntity<?> connect(@Valid @RequestBody CreateConnectionRequest connectRequest) {
    return ResponseEntity.ok(connectionService.handleConnectRequest(connectRequest));
  }
}
