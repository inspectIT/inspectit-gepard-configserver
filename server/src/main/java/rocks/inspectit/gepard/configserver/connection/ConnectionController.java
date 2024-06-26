/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection;

import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;

@RestController
@RequestMapping("/api/v1/connections")
@RequiredArgsConstructor
public class ConnectionController {

  private final ConnectionService connectionService;

  @PostMapping
  public ResponseEntity<?> connect(@Valid @RequestBody CreateConnectionRequest connectRequest) {
    return ResponseEntity.ok(connectionService.handleConnectRequest(connectRequest));
  }

  @GetMapping("/connect")
  public ResponseEntity<?> connect(
      @RequestHeader("x-gepard-service-name") String agentName,
      @RequestHeader("x-gepard-version") String gepardVersion,
      @RequestHeader("x-gepard-otel-version") String otelVersion,
      @RequestHeader("x-gepard-pid") Long pid,
      @RequestHeader("x-gepard-start-time") Long startTime,
      @RequestHeader("x-gepard-java-version") String javaVersion) {
    return ResponseEntity.ok(
        connectionService.handleConnectRequestFromHeaders(
            agentName, gepardVersion, otelVersion, pid, startTime, javaVersion));
  }

  @GetMapping
  public ResponseEntity<?> getConnections() {
    return ResponseEntity.ok(connectionService.getConnections());
  }

  @GetMapping("/{id}")
  public ResponseEntity<?> getConnection(@PathVariable UUID id) {
    return ResponseEntity.ok(connectionService.getConnection(id));
  }
}
