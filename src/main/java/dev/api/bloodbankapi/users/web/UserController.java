package dev.api.bloodbankapi.users.web;


import dev.api.bloodbankapi.users.domain.UserDto;
import dev.api.bloodbankapi.users.domain.UserService;
import dev.api.bloodbankapi.users.exceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import lombok.AllArgsConstructor;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
@CrossOrigin("*") //TODO REMOVE THIS BEFORE DEPLOYMENT
public class UserController {

  private final UserService userService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public UserDto create( @RequestBody UserDto dto) {
    return userService.create(dto);
  }

//  @PutMapping("/{id}")
//  @ResponseStatus(HttpStatus.OK)
//  public UserDto update(@PathVariable Long id, @Validated @RequestBody UserDto dto) throws UserNotFoundException {
//    return userService.update(id, dto);
//  }

  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  public UserDto get(@PathVariable Long id) throws UserNotFoundException {
    return userService.get(id);
  }

  @GetMapping
  @ResponseStatus(HttpStatus.OK)
  public List<UserDto> list() {
    return userService.list();
  }

//  @DeleteMapping("/{id}")
//  @ResponseStatus(HttpStatus.NO_CONTENT)
//  public void delete(@PathVariable Long id) throws UserNotFoundException {
//    userService.delete(id);
}

