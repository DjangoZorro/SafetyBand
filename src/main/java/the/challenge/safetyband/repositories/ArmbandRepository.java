package the.challenge.safetyband.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import the.challenge.safetyband.domain.Armband;

@Repository
public interface ArmbandRepository extends CrudRepository<Armband, Long> {
}
