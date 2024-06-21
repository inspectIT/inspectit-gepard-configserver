package rocks.inspectit.gepard.configserver.connection.model;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.util.Date;

@Data
@AllArgsConstructor
@Builder
public class ConnectRequest {

    @NotNull(message = "Service name must not be null")
    private String serviceName;
    @NotNull(message = "PID must not be null")
    private String pid;
    @NotNull(message = "Gepard version must not be null")
    private String gepardVersion;
    @NotNull(message = "Otel version must not be null")
    private String otelVersion;
    @NotNull(message = "Last JVM restart must not be null")
    private Date lastJVMRestart;

}
