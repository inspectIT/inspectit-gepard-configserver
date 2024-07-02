/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import rocks.inspectit.gepard.configserver.connection.ConnectionRepository;
import rocks.inspectit.gepard.configserver.connection.model.*;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionResponse;

/**
 * Service-Implementation for handling agent connection requests.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class AgentConnectionServiceImpl implements AgentConnectionService {

  private final ConnectionRepository connectionRepository;

  private final ConnectionDtoMapper connectionMapper;

  /**
   * Handles a connection request from an agent.
   * @param connectRequest
   * @return
   */
  @Override
  public CreateConnectionResponse handleConnectRequest(CreateConnectionRequest connectRequest) {
    return createConnectResponse(connectRequest);
  }

  /**
   * Creates a connection response based on the given connection request.
   * @param connectRequest The connection request.
   * @return CreateConnectionResponse The connection response.
   */
  private CreateConnectionResponse createConnectResponse(CreateConnectionRequest connectRequest) {

    if (!connectionRepository.existsByAgentPid(connectRequest.pid())) {

      Connection connection = connectionMapper.toConnection(connectRequest);

      connectionRepository.save(connection);

      log.info("Connection saved: {}", connection);

      return connectionMapper.toConnectionSuccessfulResponse(
          connection, ConnectRequestType.CONNECT.toString());
    } else {
      Connection connection =
          connectionRepository.findByAgentPid(connectRequest.pid()).orElseThrow();

      log.info("Connection reconnected: {}", connection);

      return connectionMapper.toConnectionSuccessfulResponse(
          connection, ConnectRequestType.RECONNECT.toString());
    }
  }
}
