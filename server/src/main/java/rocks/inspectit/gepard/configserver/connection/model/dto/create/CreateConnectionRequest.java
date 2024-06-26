/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model.dto.create;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

@Builder
public record CreateConnectionRequest(
    @NotNull String serviceName,
    @NotNull String gepardVersion,
    @NotNull String otelVersion,
    @NotNull Long pid,
    @NotNull Long startTime,
    @NotNull String javaVersion) {}
