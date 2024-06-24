package rocks.inspectit.gepard.configserver.connection.model;

import jakarta.persistence.*;
import lombok.*;
import rocks.inspectit.gepard.configserver.agent.Agent;

import java.util.UUID;

@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Embedded
    private Agent agent;
}
