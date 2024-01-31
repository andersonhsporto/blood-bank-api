package dev.api.bloodbankapi.users.web;


import dev.api.bloodbankapi.users.domain.UserService;
import dev.api.bloodbankapi.users.exceptions.PasswordUserNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin("*") //TODO REMOVE THIS BEFORE DEPLOYMENT
public class UserController implements IUserApi {

  private final UserService userService;

  @Override
  public ResponseEntity<?> getUserById(Long id) throws PasswordUserNotFoundException {
    return userService.getUserById(id);
  }

  @Override
  public ResponseEntity<?> updateBloodType(Long id, String bloodType)
      throws PasswordUserNotFoundException {
    return userService.updateBloodType(id, bloodType);
  }

  @ExceptionHandler(PasswordUserNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ResponseEntity<String> userNotFoundException(PasswordUserNotFoundException ex) {
    return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
  }

}

