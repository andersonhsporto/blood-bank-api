package dev.api.bloodbankapi.auth.domain;

import dev.api.bloodbankapi.users.base.RoleEnum;
import dev.api.bloodbankapi.users.domain.UserDto;
import dev.api.bloodbankapi.users.domain.UserEntity;
import dev.api.bloodbankapi.users.domain.UserRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private final UserRepository userRepository;

  public UserDto createUser(SignupDTO dto) {
    if (userRepository.findByEmail(dto.email()).isPresent()) {
      return null;
    }
    UserEntity user = SignupDTO.toEntity(dto);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date = LocalDate.parse(dto.localDate(), formatter);

    user.setDateOfBirth(date);

    String password = new BCryptPasswordEncoder().encode(dto.password());

    user.setPassword(password);

    userRepository.save(user);

    return UserDto.fromEntity(user);
  }

  public UserDto signupDonorUser(SignupDTO signupDTO) {
    if (userRepository.findByEmail(signupDTO.email()).isPresent()) {
      return null;
    }

    UserEntity user = SignupDTO.toEntity(signupDTO);

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date = LocalDate.parse(signupDTO.localDate(), formatter);

    user.setDateOfBirth(date);
    user.setRole(RoleEnum.DONOR);

    String password = new BCryptPasswordEncoder().encode(signupDTO.password());

    user.setPassword(password);

    userRepository.save(user);

    return UserDto.fromEntity(user);
  }
}
