package rocks.inspectit.gepard.configserver.agent;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


@Data
@Builder
@Embeddable
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

    // The Service name, which is provided by the agent
    @Nonnull
    private String serviceName;
    @Nonnull
    private Long pid;
    @Nonnull
    private String gepardVersion;
    @Nonnull
    private String otelVersion;
    @Nonnull
    private Instant startTime;
    @Nonnull
    private String javaVersion;

}
