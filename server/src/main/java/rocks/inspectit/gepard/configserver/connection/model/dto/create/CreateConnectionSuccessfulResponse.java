/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model.dto.create;

import java.util.UUID;

public record CreateConnectionSuccessfulResponse(
    UUID id,
    String serviceName,
    String gepardVersion,
    String otelVersion,
    Long pid,
    Long startTime,
    String javaVersion,
    String requestType,
    boolean isSuccess)
    implements CreateConnectionResponse {}
