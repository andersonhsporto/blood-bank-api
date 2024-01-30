package dev.api.bloodbankapi.users.domain;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

  Optional<UserEntity> findUserEntitiesByUsername(String username);

  Optional<UserEntity> findByEmailAndUsername(String email, String username);

  Optional<UserEntity> findByEmail(String email);

}
