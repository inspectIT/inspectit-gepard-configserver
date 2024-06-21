package rocks.inspectit.gepard.configserver.connection;

import rocks.inspectit.gepard.configserver.connection.model.ConnectRequest;
import rocks.inspectit.gepard.configserver.connection.model.ConnectResponse;

public interface ConnectionService {
    ConnectResponse handleConnectRequest(ConnectRequest connectRequest);
}
