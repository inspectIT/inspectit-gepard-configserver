package rocks.inspectit.gepard.configserver.connection.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ConnectRequest {

    @NotNull(message = "Agent name must not be null")
    private String serviceName;
    @NotNull(message = "Gepard version must not be null")
    private String gepardVersion;
    @NotNull(message = "Otel version must not be null")
    private String otelVersion;
    @NotNull(message = "PID must not be null")
    private Long pid;
    @NotNull(message = "Start Time must not be null")
    private String startTime;
    @NotNull(message = "Java version must not be null")
    private String javaVersion;

}
