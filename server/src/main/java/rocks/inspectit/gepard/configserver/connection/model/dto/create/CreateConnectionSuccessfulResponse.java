/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model.dto.create;

import java.util.UUID;

/**
 * Represents a successful connection response.
 *
 * @param id
 * @param serviceName
 * @param gepardVersion
 * @param otelVersion
 * @param pid
 * @param startTime
 * @param javaVersion
 * @param requestType
 * @param isSuccess
 */
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
