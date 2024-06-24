package rocks.inspectit.gepard.configserver.connection;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.inspectit.gepard.configserver.agent.Agent;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequest;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequestType;
import rocks.inspectit.gepard.configserver.connection.model.ConnectResponse;
import rocks.inspectit.gepard.configserver.connection.model.Connection;

import java.time.Instant;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.UUID;


@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

    @Autowired
    private final ConnectionRepository connectionRepository;

    public ConnectResponse handleConnectRequest(ConnectRequest agentConnectRequest) {
        return createConnectResponse(agentConnectRequest);
    }

    @Override
    public ConnectResponse handleConnectRequest(String agentName, String gepardVersion, String otelVersion, Long pid, String startTime, String javaVersion) {
        ConnectRequest agentConnectRequest = ConnectRequest.builder()
                .serviceName(agentName)
                .gepardVersion(gepardVersion)
                .otelVersion(otelVersion)
                .pid(pid)
                .startTime(startTime)
                .javaVersion(javaVersion)
                .build();

        return createConnectResponse(agentConnectRequest);
    }

    @Override
    public List<Connection> getConnections() {
        return connectionRepository.findAll();
    }

    @Override
    public Connection getConnection(UUID id) {
        return connectionRepository.findById(id).orElseThrow();
    }

    private ConnectResponse createConnectResponse(ConnectRequest agentConnectRequest) {

        Instant startTime = null;

        if(!connectionRepository
                .existsByAgentPid(agentConnectRequest.getPid())) {
            try {
                startTime = createInstantFromString(agentConnectRequest.getStartTime());

            } catch(DateTimeParseException e) {
                return ConnectResponse.builder().success(false).message("Start Date could not be parsed.").build();
            }
            Agent agent = Agent.builder()
                    .serviceName(agentConnectRequest.getServiceName())
                    .pid(agentConnectRequest.getPid())
                    .gepardVersion(agentConnectRequest.getGepardVersion())
                    .otelVersion(agentConnectRequest.getOtelVersion())
                    .startTime(startTime)
                    .javaVersion(agentConnectRequest.getJavaVersion())
                    .build();

            Connection connection = Connection.builder().agent(agent).build();

            connectionRepository.save(connection);

            return ConnectResponse.builder().success(true).agentRequestType(ConnectRequestType.CONNECT).connection(connection).build();
        }
        else {
            Connection connection = connectionRepository.findByAgentPid(agentConnectRequest.getPid()).orElseThrow();
            return ConnectResponse.builder().success(true).agentRequestType(ConnectRequestType.RECONNECT).connection(connection).build();
        }
    }

    private Instant createInstantFromString(String dateString) throws DateTimeParseException {
        return Instant.parse(dateString);
    }

}