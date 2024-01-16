package dev.api.bloodbankapi.auth.domain;

import dev.api.bloodbankapi.users.base.RoleEnum;
import dev.api.bloodbankapi.users.domain.UserDto;
import dev.api.bloodbankapi.users.domain.UserEntity;
import dev.api.bloodbankapi.users.domain.UserRepository;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.apache.catalina.User;
import org.springframework.cglib.core.Local;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class AuthService {

  private final UserRepository userRepository;

  public UserDto createUser(SignupDTO dto) {
    UserEntity user = new UserEntity();

    user.setName(dto.name());
    user.setEmail(dto.email());
    user.setUsername(dto.email());

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    LocalDate date = LocalDate.parse(dto.localDate(), formatter);

    user.setDateOfBirth(date);

    user.setRole(RoleEnum.ADMIN);

    String password = new BCryptPasswordEncoder().encode(dto.password());

    user.setPassword(password);

    userRepository.save(user);

    System.out.println("rt");

    return UserDto.fromEntity(user);
  }

}
