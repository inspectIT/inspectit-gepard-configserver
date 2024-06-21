package rocks.inspectit.gepard.configserver.connection;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequest;

@RestController
@RequestMapping("/api/v1/connection")
@RequiredArgsConstructor
public class ConnectionController {

    private final ConnectionService agentService;

    @PostMapping("/connect")
    public ResponseEntity<?> connect(@Valid @RequestBody ConnectRequest connectRequest) {
        return ResponseEntity.ok(agentService.handleConnectRequest(connectRequest));
    }

}