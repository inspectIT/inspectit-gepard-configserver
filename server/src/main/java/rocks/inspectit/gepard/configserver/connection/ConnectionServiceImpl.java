package rocks.inspectit.gepard.configserver.connection;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.inspectit.gepard.configserver.agent.Agent;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequest;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequestType;
import rocks.inspectit.gepard.configserver.connection.model.ConnectResponse;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    private final ConnectionRepository connectionRepository;

    public ConnectResponse handleConnectRequest(ConnectRequest agentConnectRequest) {
        if(!connectionRepository.existsByPid(agentConnectRequest.getPid())) {
            Agent agent = Agent.builder()
                    .serviceName(agentConnectRequest.getServiceName())
                    .pid(agentConnectRequest.getPid())
                    .gepardVersion(agentConnectRequest.getGepardVersion())
                    .otelVersion(agentConnectRequest.getOtelVersion())
                    .lastJVMRestart(agentConnectRequest.getLastJVMRestart())
                    .build();
            connectionRepository.save(agent);
            return ConnectResponse.builder().success(true).agentRequestType(ConnectRequestType.CONNECT).agentInfo(agent).build();
        }
        else {
            Agent agent = connectionRepository.findByPid(agentConnectRequest.getPid()).orElseThrow();
            return ConnectResponse.builder().success(true).agentRequestType(ConnectRequestType.RECONNECT).agentInfo(agent).build();
        }
    }
}