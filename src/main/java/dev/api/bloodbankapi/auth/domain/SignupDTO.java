package dev.api.bloodbankapi.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SignupDTO(
    String name,
    String email,
    String password,
    @JsonProperty("date_of_birth")
    String localDate
) {

}
