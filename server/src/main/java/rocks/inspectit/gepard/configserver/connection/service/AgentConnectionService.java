/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.service;

import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionResponse;

public interface AgentConnectionService {
  CreateConnectionResponse handleConnectRequest(CreateConnectionRequest connectRequest);
}
