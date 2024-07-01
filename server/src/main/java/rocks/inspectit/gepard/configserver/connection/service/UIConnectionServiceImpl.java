/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.service;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import rocks.inspectit.gepard.configserver.connection.ConnectionRepository;
import rocks.inspectit.gepard.configserver.connection.model.Connection;
import rocks.inspectit.gepard.configserver.connection.model.dto.read.ReadConnectionDTO;

@RequiredArgsConstructor
@Service
public class UIConnectionServiceImpl implements UIConnectionService {

  private final ConnectionRepository connectionRepository;

  @Override
  public List<ReadConnectionDTO> getConnections() {
    List<Connection> connections = connectionRepository.findAll();
    return ReadConnectionDTO.fromConnections(connections);
  }

  @Override
  public ReadConnectionDTO getConnection(UUID id) {
    Connection connection = connectionRepository.findById(id).orElseThrow();
    return ReadConnectionDTO.fromConnection(connection);
  }
}
