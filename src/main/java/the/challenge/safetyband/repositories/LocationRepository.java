package the.challenge.safetyband.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import the.challenge.safetyband.domain.Location;

@Repository
public interface LocationRepository extends CrudRepository<Location, Long> {
}
