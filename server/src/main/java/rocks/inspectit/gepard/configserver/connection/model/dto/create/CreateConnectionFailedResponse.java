/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model.dto.create;

/**
 * Represents a failed connection response.
 * Not used yet.
 */
public record CreateConnectionFailedResponse(String message, boolean isSuccess, String requestType)
    implements CreateConnectionResponse {}
