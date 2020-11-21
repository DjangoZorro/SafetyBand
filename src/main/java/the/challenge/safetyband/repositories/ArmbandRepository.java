package the.challenge.safetyband.repositories;

import org.springframework.data.repository.CrudRepository;
import the.challenge.safetyband.domain.Armband;

public interface ArmbandRepository extends CrudRepository<Armband, Integer> {
}
