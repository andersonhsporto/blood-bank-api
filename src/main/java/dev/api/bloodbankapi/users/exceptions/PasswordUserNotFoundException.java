package dev.api.bloodbankapi.users.exceptions;

public class PasswordUserNotFoundException extends Exception {

  private static final long serialVersionUID = 1L;

  public PasswordUserNotFoundException(String message) {
    super(message);
  }

  public PasswordUserNotFoundException() {
    super("user or password not found");
  }
}