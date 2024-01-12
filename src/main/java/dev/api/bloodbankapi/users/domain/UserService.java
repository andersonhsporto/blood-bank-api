package dev.api.bloodbankapi.users.domain;

import dev.api.bloodbankapi.users.exceptions.UserNotFoundException;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserService {

  private final UserRepository userRepository;


  public UserDto create(UserDto dto) {
    var UserEntity = UserDto.toEntity(dto);
    // TODO: inject password encoder and encode password

    userRepository.save(UserEntity);
    return UserDto.fromEntity(UserEntity);
  }

  public UserDto get(Long id) throws UserNotFoundException {
    var userEntity = userRepository.findById(id).orElseThrow(UserNotFoundException::new);

    return UserDto.fromEntity(userEntity);
  }


  public List<UserDto> list() {
    var userEntities = userRepository.findAll();

    return UserDto.fromEntityList(userEntities);
  }

  public ResponseEntity<UserDto> getUserById(Long id) {
    return null;
  }

}
