/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rocks.inspectit.gepard.configserver.agent.Agent;
import rocks.inspectit.gepard.configserver.connection.model.*;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionResponse;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionSuccessfulResponse;
import rocks.inspectit.gepard.configserver.connection.model.dto.read.ReadConnectionDTO;

@ExtendWith(MockitoExtension.class)
public class ConnectionServiceImplTest {

  @Mock private ConnectionRepository connectionRepository;

  @Mock private ConnectionDtoMapper connectionMapper;

  @InjectMocks private ConnectionServiceImpl connectionService;

  private CreateConnectionRequest connectRequest;
  private CreateConnectionSuccessfulResponse connectionResponse;

  @Test
  public void testHandleConnectRequestNewAgent() {

    connectRequest =
        CreateConnectionRequest.builder()
            .serviceName("agentName")
            .gepardVersion("gepardVersion")
            .otelVersion("otelVersion")
            .pid(123L)
            .startTime(1719394483600L)
            .javaVersion("javaVersion")
            .build();

    connectionResponse =
        new CreateConnectionSuccessfulResponse(
            new UUID(0, 0),
            "agentName",
            "gepardVersion",
            "otelVersion",
            123L,
            1719394483600L,
            "javaVersion",
            "CONNECT",
            true);
    // When calling the repository just return false, instead of actually checking if the agent
    // exists.
    when(connectionRepository.existsByAgentPid(connectRequest.pid())).thenReturn(false);
    when(connectionMapper.toConnection(any(CreateConnectionRequest.class)))
        .thenReturn(new Connection());
    when(connectionMapper.toConnectionSuccessfulResponse(
            any(Connection.class), eq(ConnectRequestType.CONNECT.toString())))
        .thenReturn(connectionResponse);

    // Call the method to test
    CreateConnectionResponse result = connectionService.handleConnectRequest(connectRequest);

    // Check if the result is the expected ConnectionSuccessfulResponse object
    assertEquals(connectionResponse, result);
  }

  @Test
  public void testHandleConnectRequestExistingAgent() {

    connectRequest =
        CreateConnectionRequest.builder()
            .serviceName("agentName")
            .gepardVersion("gepardVersion")
            .otelVersion("otelVersion")
            .pid(123L)
            .startTime(1719394483600L)
            .javaVersion("javaVersion")
            .build();

    connectionResponse =
        new CreateConnectionSuccessfulResponse(
            new UUID(0, 0),
            "agentName",
            "gepardVersion",
            "otelVersion",
            123L,
            1719394483600L,
            "javaVersion",
            "RECONNECT",
            true);

    when(connectionRepository.existsByAgentPid(connectRequest.pid())).thenReturn(true);
    when(connectionRepository.findByAgentPid(connectRequest.pid()))
        .thenReturn(Optional.of(new Connection()));
    when(connectionMapper.toConnectionSuccessfulResponse(
            any(Connection.class), eq(ConnectRequestType.RECONNECT.toString())))
        .thenReturn(connectionResponse);
    CreateConnectionResponse result = connectionService.handleConnectRequest(connectRequest);

    assertEquals(connectionResponse, result);
  }

  @Test
  public void testHandleConnectRequestFromHeaders() {
    String agentName = "agentName";
    String gepardVersion = "gepardVersion";
    String otelVersion = "otelVersion";
    Long pid = 123L;
    Long startTime = 1719394483600L;
    String javaVersion = "javaVersion";

    when(connectionRepository.existsByAgentPid(123L)).thenReturn(true);
    when(connectionRepository.findByAgentPid(123L)).thenReturn(Optional.of(new Connection()));
    when(connectionMapper.toConnectionSuccessfulResponse(
            any(Connection.class), eq(ConnectRequestType.RECONNECT.toString())))
        .thenReturn(connectionResponse);

    CreateConnectionResponse result =
        connectionService.handleConnectRequestFromHeaders(
            agentName, gepardVersion, otelVersion, pid, startTime, javaVersion);

    assertEquals(connectionResponse, result);
  }

  @Test
  public void testGetConnection() {

    Connection connection =
        Connection.builder()
            .id(new UUID(0, 0))
            .agent(
                Agent.builder()
                    .serviceName("serviceName")
                    .gepardVersion("gepardVersion")
                    .otelVersion("otelVersion")
                    .pid(123L)
                    .startTime(Instant.ofEpochMilli(1719394483600L))
                    .javaVersion("javaVersion")
                    .build())
            .build();
    ReadConnectionDTO readConnectionDTO =
        new ReadConnectionDTO(
            new UUID(0, 0),
            "serviceName",
            "gepardVersion",
            "otelVersion",
            123L,
            1719394483600L,
            "javaVersion");

    when(connectionRepository.findById(any(UUID.class))).thenReturn(Optional.of(connection));

    ReadConnectionDTO result = connectionService.getConnection(new UUID(0, 0));

    assertEquals(readConnectionDTO, result);
  }

  @Test
  public void testGetConnections() {
    Connection connection =
        Connection.builder()
            .id(new UUID(0, 0))
            .agent(
                Agent.builder()
                    .serviceName("serviceName")
                    .gepardVersion("gepardVersion")
                    .otelVersion("otelVersion")
                    .pid(123L)
                    .startTime(Instant.ofEpochMilli(1719394483600L))
                    .javaVersion("javaVersion")
                    .build())
            .build();
    List<ReadConnectionDTO> readConnectionDTO =
        List.of(
            new ReadConnectionDTO(
                new UUID(0, 0),
                "serviceName",
                "gepardVersion",
                "otelVersion",
                123L,
                1719394483600L,
                "javaVersion"));

    when(connectionRepository.findAll()).thenReturn(List.of(connection));
    List<ReadConnectionDTO> result = connectionService.getConnections();

    assertEquals(readConnectionDTO, result);
  }
}
