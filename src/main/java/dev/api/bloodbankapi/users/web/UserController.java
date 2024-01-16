package dev.api.bloodbankapi.users.web;


import dev.api.bloodbankapi.users.domain.UserDto;
import dev.api.bloodbankapi.users.domain.UserService;
import dev.api.bloodbankapi.users.exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*") //TODO REMOVE THIS BEFORE DEPLOYMENT
public class UserController implements IUserApi {

  private final UserService userService;

  @Override
  public ResponseEntity<?> getUserById(Long id) throws UserNotFoundException {
    return userService.getUserById(id);
  }

  @ExceptionHandler(UserNotFoundException.class)
  public ResponseEntity<String> userNotFoundException(UserNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

}

