package rocks.inspectit.gepard.configserver.connection.model;

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
    private Agent agentInfo;
    private boolean success;
    private ConnectRequestType agentRequestType;
}
