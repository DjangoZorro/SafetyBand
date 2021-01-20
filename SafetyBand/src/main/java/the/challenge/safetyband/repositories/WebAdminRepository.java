package the.challenge.safetyband.repositories;

import org.springframework.data.repository.CrudRepository;
import the.challenge.safetyband.domain.WebAdmin;

import java.util.Optional;

public interface WebAdminRepository extends CrudRepository<WebAdmin, Long> {
    Optional<WebAdmin> findByEmail(String email);
}
