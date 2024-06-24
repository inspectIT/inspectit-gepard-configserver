package rocks.inspectit.gepard.configserver.connection.model;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import rocks.inspectit.gepard.configserver.agent.Agent;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class ConnectResponse {
    private Connection connection;
    @NotNull
    private boolean success;
    private ConnectRequestType agentRequestType;
    private String message;
}
