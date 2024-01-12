package dev.api.bloodbankapi.auth.domain;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TokenRepository extends JpaRepository<TokenEntity, Long> {

  List<TokenEntity> findAllByUserId(Long userId);

  Optional<TokenEntity> findByToken(String token);
}
