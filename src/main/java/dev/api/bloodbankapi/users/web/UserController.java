package dev.api.bloodbankapi.users.web;


import dev.api.bloodbankapi.users.domain.UserDto;
import dev.api.bloodbankapi.users.domain.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*") //TODO REMOVE THIS BEFORE DEPLOYMENT
public class UserController implements IUserApi {

  private final UserService userService;

  @Override
  public ResponseEntity<UserDto> getUserById(Long id) {
    return userService.getUserById(id);
  }

}

