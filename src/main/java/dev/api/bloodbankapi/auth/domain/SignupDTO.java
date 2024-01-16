package dev.api.bloodbankapi.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.time.LocalDate;

public record SignupDTO(
    String name,
    String email,
    String password,
    @JsonProperty("date_of_birth")
    String localDate
) {

}
