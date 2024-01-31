package dev.api.bloodbankapi.auth.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import dev.api.bloodbankapi.users.base.RoleEnum;
import dev.api.bloodbankapi.users.domain.UserEntity;

public record SignupDTO(

    @JsonProperty("name")
    String name,

    @JsonProperty("email")
    String email,

    @JsonProperty("username")
    String password,

    @JsonProperty("role")
    String role,

    @JsonProperty("date_of_birth")
    String localDate
) {

    public static UserEntity toEntity(SignupDTO dto) {
        UserEntity user = new UserEntity();

        user.setName(dto.name());
        user.setEmail(dto.email());
        user.setUsername(dto.email());

        RoleEnum role = RoleEnum.valueOf(dto.role());

        user.setRole(role);

        return user;
    }

}
