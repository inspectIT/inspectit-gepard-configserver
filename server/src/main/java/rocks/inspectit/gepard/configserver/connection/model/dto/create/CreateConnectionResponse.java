/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model.dto.create;

public interface CreateConnectionResponse {
  boolean isSuccess();

  String requestType();
}
