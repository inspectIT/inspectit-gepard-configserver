package rocks.inspectit.gepard.configserver.agent;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Agent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    // The Service name, which is provided by the agent
    @Nonnull
    private String serviceName;
    @Nonnull
    private String pid;
    @Nonnull
    private String gepardVersion;
    @Nonnull
    private String otelVersion;
    @Nonnull
    private Date lastJVMRestart;

}
