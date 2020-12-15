package the.challenge.safetyband.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import the.challenge.safetyband.domain.ConfirmationToken;

import java.util.Optional;

@Repository
public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, Long> {
    Optional<ConfirmationToken> findConfirmationTokenByConfirmationToken(String token);
}
