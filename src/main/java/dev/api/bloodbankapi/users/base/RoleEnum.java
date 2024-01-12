package dev.api.bloodbankapi.users.base;

public enum RoleEnum {
  ADMIN("administrador"),
  USER("usuario"),
  DONOR("doador");

  private String description;

  RoleEnum(String description) {
    this.description = description;
  }

  public String getDescription() {
    return this.description;
  }
}
