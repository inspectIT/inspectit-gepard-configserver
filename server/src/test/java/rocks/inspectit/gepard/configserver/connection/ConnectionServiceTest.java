package rocks.inspectit.gepard.configserver.connection;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequest;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequestType;
import rocks.inspectit.gepard.configserver.connection.model.ConnectResponse;
import rocks.inspectit.gepard.configserver.connection.model.Connection;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ConnectionServiceTest {

    @Mock
    private ConnectionRepository connectionRepository;

    @InjectMocks
    private ConnectionServiceImpl connectionService;

    @Test
    public void testHandleConnectRequestNewAgent() {
        // GIVEN
        ConnectRequest connectRequest = ConnectRequest.builder()
                .serviceName("service-name")
                .pid(656345L)
                .gepardVersion("0.0.1")
                .otelVersion("1.26.8")
                .startTime("2021-08-12T12:00:00Z")
                .javaVersion("11.0.11")
                .build();

        when(connectionRepository.existsByAgentPid(connectRequest.getPid())).thenReturn(false);

        // WHEN
        ConnectResponse response = connectionService.handleConnectRequest(connectRequest);

        // THEN
        assertEquals(response.getAgentRequestType(), ConnectRequestType.CONNECT);
        assertTrue(response.isSuccess());
    }

    @Test
    public void testHandleConnectRequestExistingAgent() {

        // GIVEN
        ConnectRequest connectRequest = ConnectRequest.builder()
                .serviceName("service-name")
                .pid(56328758L)
                .gepardVersion("0.0.1")
                .otelVersion("1.26.8")
                .startTime("2021-08-12T12:00:00Z")
                .javaVersion("11.0.11")
                .build();

        when(connectionRepository.existsByAgentPid(connectRequest.getPid())).thenReturn(true);
        when(connectionRepository.findByAgentPid(connectRequest.getPid())).thenReturn(Optional.of(new Connection()));

        // WHEN
        ConnectResponse response = connectionService.handleConnectRequest(connectRequest);

        // THEN
        assertEquals(response.getAgentRequestType(), ConnectRequestType.RECONNECT);
        assertTrue(response.isSuccess());
    }

}
