/* (C) 2024 */
package rocks.inspectit.gepard.configserver.connection;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.inspectit.gepard.configserver.connection.model.Connection;

/**
 * Repository for the connection entity.
 */
@Repository
public interface ConnectionRepository extends JpaRepository<Connection, UUID> {

  boolean existsByAgentPid(Long pid);

  Optional<Connection> findByAgentPid(Long pid);
}
