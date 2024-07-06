/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
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
import rocks.inspectit.gepard.configserver.connection.ConnectionRepository;
import rocks.inspectit.gepard.configserver.connection.model.Connection;
import rocks.inspectit.gepard.configserver.connection.model.ConnectionDtoMapperTest;
import rocks.inspectit.gepard.configserver.connection.model.dto.read.ReadConnectionDTO;

@ExtendWith(MockitoExtension.class)
public class UIConnectionServiceImplTest {

  @Mock private ConnectionRepository connectionRepository;

  @Mock private ConnectionDtoMapperTest connectionMapper;

  @InjectMocks private UIConnectionServiceImpl connectionService;

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
