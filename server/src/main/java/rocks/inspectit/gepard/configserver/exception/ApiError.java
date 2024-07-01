/* (C) 2024 */
package rocks.inspectit.gepard.configserver.exception;

import java.time.LocalDateTime;
import java.util.List;

public record ApiError(String path, List<String> errors, int statusCode, LocalDateTime timestamp) {}
