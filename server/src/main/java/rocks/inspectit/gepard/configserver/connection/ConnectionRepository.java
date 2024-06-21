package rocks.inspectit.gepard.configserver.connection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import rocks.inspectit.gepard.configserver.agent.Agent;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConnectionRepository extends JpaRepository<Agent, UUID> {

    boolean existsByPid(String pid);

    Optional<Agent> findByPid(String pid);

}
