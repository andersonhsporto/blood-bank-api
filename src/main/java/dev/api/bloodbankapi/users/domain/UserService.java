package dev.api.bloodbankapi.users.domain;

import dev.api.bloodbankapi.users.base.BloodTypeEnum;
import dev.api.bloodbankapi.users.exceptions.PasswordUserNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;

  public UserDto get(Long id) throws PasswordUserNotFoundException {
    var userEntity = userRepository.findById(id).orElseThrow(PasswordUserNotFoundException::new);

    return UserDto.fromEntity(userEntity);
  }


  public List<UserDto> list() {
    var userEntities = userRepository.findAll();

    return UserDto.fromEntityList(userEntities);
  }

  public ResponseEntity<?> getUserById(Long id) throws PasswordUserNotFoundException {
    var user = userRepository.findById(id).orElseThrow(
        () -> new PasswordUserNotFoundException(
            String.format(
                "User with id [%d] not found",
                id
            )
        )
    );

    UserDto dto = UserDto.fromEntity(user);

    return new ResponseEntity<>(dto, HttpStatus.OK);
  }

  @PreAuthorize("hasAnyAuthority({'ADMIN', 'USER'})")
  public ResponseEntity<?> updateBloodType(Long id, String bloodType)
      throws PasswordUserNotFoundException {
    UserEntity user = userRepository.findById(id).orElseThrow(
        () -> new PasswordUserNotFoundException(
            String.format(
                "User with id [%d] not found",
                id
            )
        )
    );

    BloodTypeEnum bloodTypeEnum = BloodTypeEnum.valueOf(bloodType);

    user.setBloodType(bloodTypeEnum);

    userRepository.save(user);

    return new ResponseEntity<>(HttpStatus.OK);
  }
}
