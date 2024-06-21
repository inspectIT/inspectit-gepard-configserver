package rocks.inspectit.gepard.configserver.connection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import rocks.inspectit.gepard.configserver.agent.Agent;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequest;
import rocks.inspectit.gepard.configserver.connection.model.ConnectRequestType;
import rocks.inspectit.gepard.configserver.connection.model.ConnectResponse;

import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
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
                .pid("21434")
                .gepardVersion("0.0.1")
                .otelVersion("1.26.8")
                .lastJVMRestart(new Date())
                .build();

        when(connectionRepository.existsByPid(connectRequest.getPid())).thenReturn(false);

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
                .pid("21434")
                .gepardVersion("0.0.1")
                .otelVersion("1.26.8")
                .lastJVMRestart(new Date())
                .build();

        when(connectionRepository.existsByPid(connectRequest.getPid())).thenReturn(true);
        when(connectionRepository.findByPid(connectRequest.getPid())).thenReturn(Optional.of(new Agent()));

        // WHEN
        ConnectResponse response = connectionService.handleConnectRequest(connectRequest);

        // THEN
        assertEquals(response.getAgentRequestType(), ConnectRequestType.RECONNECT);
        assertTrue(response.isSuccess());
    }

}
