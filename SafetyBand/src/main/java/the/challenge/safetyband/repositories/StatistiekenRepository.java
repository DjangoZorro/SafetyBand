package the.challenge.safetyband.repositories;

import org.springframework.data.repository.CrudRepository;
import the.challenge.safetyband.domain.Statistieken;

public interface StatistiekenRepository extends CrudRepository<Statistieken, Integer> {
}
