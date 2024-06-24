package rocks.inspectit.gepard.configserver.connection;

import rocks.inspectit.gepard.configserver.connection.model.ConnectRequest;
import rocks.inspectit.gepard.configserver.connection.model.ConnectResponse;
import rocks.inspectit.gepard.configserver.connection.model.Connection;

import java.text.ParseException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface ConnectionService {
    ConnectResponse handleConnectRequest(ConnectRequest connectRequest);
    ConnectResponse handleConnectRequest(String agentName, String gepardVersion, String otelVersion, Long pid, String startTime, String javaVersion);
    List<Connection> getConnections();

    Connection getConnection(UUID id);
}
