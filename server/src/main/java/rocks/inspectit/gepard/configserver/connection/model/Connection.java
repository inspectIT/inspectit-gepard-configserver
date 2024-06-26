/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection.model;

import jakarta.persistence.*;
import java.util.UUID;
import lombok.*;
import rocks.inspectit.gepard.configserver.agent.Agent;

/**
 * Represents a connected agent. It is an internal data structure and not exposed to the API. Let´s
 * use DTOs for the API.
 */
@Entity
@Table
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Connection {

  /** The id of the connection. This is a UUID and generate by JPA. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID id;

  /** The agent which is connected. */
  @Embedded private Agent agent;
}
