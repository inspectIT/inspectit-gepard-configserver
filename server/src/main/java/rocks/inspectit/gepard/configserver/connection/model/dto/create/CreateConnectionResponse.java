/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model.dto.create;

/**
 * Represents a connection response.
 * If the connection was successful, isSuccess is true.
 * If the connection failed, isSuccess is false.
 * requestType is the type of the request.
 * This interface is implemented by CreateConnectionSuccessfulResponse and CreateConnectionFailedResponse.
 */
public interface CreateConnectionResponse {
  boolean isSuccess();
  String requestType();
}
