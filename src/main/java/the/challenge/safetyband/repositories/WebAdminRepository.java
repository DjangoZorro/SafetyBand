package the.challenge.safetyband.repositories;

import org.springframework.data.repository.CrudRepository;
import the.challenge.safetyband.domain.WebAdmin;

public interface WebAdminRepository extends CrudRepository<WebAdmin, Integer> {
}
