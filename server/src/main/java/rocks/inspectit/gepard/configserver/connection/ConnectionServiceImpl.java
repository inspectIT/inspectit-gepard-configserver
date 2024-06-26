/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rocks.inspectit.gepard.configserver.connection.model.*;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionResponse;
import rocks.inspectit.gepard.configserver.connection.model.dto.read.ReadConnectionDTO;

@Service
@RequiredArgsConstructor
public class ConnectionServiceImpl implements ConnectionService {

  private final ConnectionRepository connectionRepository;

  @Autowired private final ConnectionDtoMapper connectionMapper;

  public CreateConnectionResponse handleConnectRequest(CreateConnectionRequest connectRequest) {
    return createConnectResponse(connectRequest);
  }

  @Override
  public CreateConnectionResponse handleConnectRequestFromHeaders(
      String agentName,
      String gepardVersion,
      String otelVersion,
      Long pid,
      Long startTime,
      String javaVersion) {
    CreateConnectionRequest connectRequest =
        CreateConnectionRequest.builder()
            .serviceName(agentName)
            .gepardVersion(gepardVersion)
            .otelVersion(otelVersion)
            .pid(pid)
            .startTime(startTime)
            .javaVersion(javaVersion)
            .build();

    return createConnectResponse(connectRequest);
  }

  @Override
  public List<ReadConnectionDTO> getConnections() {
    List<Connection> connections = connectionRepository.findAll();
    return ReadConnectionDTO.fromConnections(connections);
  }

  @Override
  public ReadConnectionDTO getConnection(UUID id) {
    Connection connection = connectionRepository.findById(id).orElseThrow();
    return ReadConnectionDTO.fromConnection(connection);
  }

  private CreateConnectionResponse createConnectResponse(CreateConnectionRequest connectRequest) {

    if (!connectionRepository.existsByAgentPid(connectRequest.pid())) {

      Connection connection = connectionMapper.toConnection(connectRequest);

      connectionRepository.save(connection);

      return connectionMapper.toConnectionSuccessfulResponse(
          connection, ConnectRequestType.CONNECT.toString());
    } else {
      Connection connection =
          connectionRepository.findByAgentPid(connectRequest.pid()).orElseThrow();
      return connectionMapper.toConnectionSuccessfulResponse(
          connection, ConnectRequestType.RECONNECT.toString());
    }
  }
}
