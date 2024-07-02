/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.service;

import java.util.List;
import java.util.UUID;
import rocks.inspectit.gepard.configserver.connection.model.dto.read.ReadConnectionDTO;

/**
 * Service-Interface for handling UI connection requests.
 */
public interface UIConnectionService {

  List<ReadConnectionDTO> getConnections();

  ReadConnectionDTO getConnection(UUID id);
}
