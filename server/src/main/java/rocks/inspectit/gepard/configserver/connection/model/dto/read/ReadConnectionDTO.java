/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model.dto.read;

import java.util.List;
import java.util.UUID;
import rocks.inspectit.gepard.configserver.connection.model.Connection;

/**
 * Represents a connection response for the UI, when reading connections.
 * @param id
 * @param serviceName
 * @param gepardVersion
 * @param otelVersion
 * @param pid
 * @param startTime
 * @param javaVersion
 */
public record ReadConnectionDTO(
    UUID id,
    String serviceName,
    String gepardVersion,
    String otelVersion,
    Long pid,
    Long startTime,
    String javaVersion) {
  public static List<ReadConnectionDTO> fromConnections(List<Connection> connections) {

    return connections.stream()
        .map(
            connection ->
                new ReadConnectionDTO(
                    connection.getId(),
                    connection.getAgent().getServiceName(),
                    connection.getAgent().getGepardVersion(),
                    connection.getAgent().getOtelVersion(),
                    connection.getAgent().getPid(),
                    connection.getAgent().getStartTime().toEpochMilli(),
                    connection.getAgent().getJavaVersion()))
        .toList();
  }

  public static ReadConnectionDTO fromConnection(Connection connection) {
    return new ReadConnectionDTO(
        connection.getId(),
        connection.getAgent().getServiceName(),
        connection.getAgent().getGepardVersion(),
        connection.getAgent().getOtelVersion(),
        connection.getAgent().getPid(),
        connection.getAgent().getStartTime().toEpochMilli(),
        connection.getAgent().getJavaVersion());
  }
}
