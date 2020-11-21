package the.challenge.safetyband.repositories;

import org.springframework.data.repository.CrudRepository;
import the.challenge.safetyband.domain.Location;

public interface LocationRepository extends CrudRepository<Location, Integer> {
}
