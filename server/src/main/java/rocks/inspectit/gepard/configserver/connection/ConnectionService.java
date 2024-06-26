/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection;

import java.util.List;
import java.util.UUID;
import org.springframework.stereotype.Service;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionRequest;
import rocks.inspectit.gepard.configserver.connection.model.dto.create.CreateConnectionResponse;
import rocks.inspectit.gepard.configserver.connection.model.dto.read.ReadConnectionDTO;

@Service
public interface ConnectionService {
  CreateConnectionResponse handleConnectRequest(CreateConnectionRequest connectRequest);

  CreateConnectionResponse handleConnectRequestFromHeaders(
      String agentName,
      String gepardVersion,
      String otelVersion,
      Long pid,
      Long startTime,
      String javaVersion);

  List<ReadConnectionDTO> getConnections();

  ReadConnectionDTO getConnection(UUID id);
}
