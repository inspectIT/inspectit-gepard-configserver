package rocks.inspectit.gepard.configserver.connection;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequest;

import java.text.DateFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/connections")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService connectionService;

    @PostMapping
    public ResponseEntity<?> connect(@Valid @RequestBody ConnectRequest connectRequest) {
        return ResponseEntity.ok(connectionService.handleConnectRequest(connectRequest));
    }

    @GetMapping("/connect")
    public ResponseEntity<?> connect(@RequestHeader("x-gepard-service-name") String agentName, @RequestHeader("x-gepard-version") String gepardVersion, @RequestHeader("x-gepard-otel-version") String otelVersion, @RequestHeader("x-gepard-pid") Long pid, @RequestHeader("x-gepard-start-time") String startTime, @RequestHeader("x-gepard-java-version") String javaVersion) {
        return ResponseEntity.ok(connectionService.handleConnectRequest(agentName, gepardVersion, otelVersion, pid, startTime, javaVersion));
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