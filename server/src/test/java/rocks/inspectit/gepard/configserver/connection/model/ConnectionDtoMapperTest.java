/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Instant;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import rocks.inspectit.gepard.configserver.agent.Agent;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionSuccessfulResponse;

public class ConnectionDtoMapperTest {

  private ConnectionDtoMapper connectionDtoMapper;

  @BeforeEach
  public void setUp() {
    connectionDtoMapper = new ConnectionDtoMapper();
  }

  @Test
  public void testToConnection() {

    //
    CreateConnectionRequest createConnectionRequest =
        CreateConnectionRequest.builder()
            .serviceName("agentName")
            .gepardVersion("gepardVersion")
            .otelVersion("otelVersion")
            .pid(123L)
            .startTime(1719394483600L)
            .javaVersion("javaVersion")
            .build();

    Connection connection = connectionDtoMapper.toConnection(createConnectionRequest);

    assertEquals("agentName", connection.getAgent().getServiceName());
    assertEquals("gepardVersion", connection.getAgent().getGepardVersion());
    assertEquals("otelVersion", connection.getAgent().getOtelVersion());
    assertEquals(123L, connection.getAgent().getPid());
    assertEquals(1719394483600L, connection.getAgent().getStartTime().toEpochMilli());
    assertEquals("javaVersion", connection.getAgent().getJavaVersion());
  }

  public void testToConnectionSuccessfulResponse() {

    Connection connection =
        Connection.builder()
            .id(new UUID(0, 0))
            .agent(
                Agent.builder()
                    .serviceName("agentName")
                    .gepardVersion("gepardVersion")
                    .otelVersion("otelVersion")
                    .pid(123L)
                    .startTime(Instant.ofEpochMilli(1719394483600L))
                    .javaVersion("javaVersion")
                    .build())
            .build();

    CreateConnectionSuccessfulResponse createConnectionSuccessfulResponse =
        connectionDtoMapper.toConnectionSuccessfulResponse(connection, "CONNECT");

    assertEquals(new UUID(0, 0), createConnectionSuccessfulResponse.id());
    assertEquals("agentName", createConnectionSuccessfulResponse.serviceName());
    assertEquals("gepardVersion", createConnectionSuccessfulResponse.gepardVersion());
    assertEquals("otelVersion", createConnectionSuccessfulResponse.otelVersion());
    assertEquals(123L, createConnectionSuccessfulResponse.pid());
    assertEquals(1719394483600L, createConnectionSuccessfulResponse.startTime());
    assertEquals("javaVersion", createConnectionSuccessfulResponse.javaVersion());
    assertEquals("CONNECT", createConnectionSuccessfulResponse.requestType());
    assertTrue(createConnectionSuccessfulResponse.isSuccess());
  }
}
