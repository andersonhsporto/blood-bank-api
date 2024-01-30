package dev.api.bloodbankapi.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.api.bloodbankapi.users.domain.UserEntity;

public record SignupDTO(
    String name,
    String email,
    String password,
    @JsonProperty("date_of_birth")
    String localDate
) {

    public static UserEntity toEntity(SignupDTO dto) {
        UserEntity user = new UserEntity();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setUsername(dto.email());

        return user;
    }

}
