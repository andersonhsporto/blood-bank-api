package dev.api.bloodbankapi.users.base;

public enum BloodTypeEnum {
  A_POSITIVE("A+"),
  A_NEGATIVE("A-"),
  B_POSITIVE("B+"),
  B_NEGATIVE("B-"),
  O_POSITIVE("O+"),
  O_NEGATIVE("O-"),
  AB_POSITIVE("AB+"),
  AB_NEGATIVE("AB-"),
  SPECIAL("SPECIAL");

  private final String bloodType;

  BloodTypeEnum(String bloodType) {
    this.bloodType = bloodType;
  }

  public String getBloodType() {
    return bloodType;
  }
}
