package dev.api.bloodbankapi.users.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import dev.api.bloodbankapi.users.base.RoleEnum;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import lombok.Builder;

@Builder
public record UserDto(

    @JsonProperty("id")
    Long id,

    @NotEmpty(message = "Name is required")
    @JsonProperty("name")
    String name,

    @NotEmpty(message = "Username is required")
    @JsonProperty("username")
    String username,

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("password")
    String password,

    @JsonInclude(Include.NON_NULL)
    @JsonProperty("role")
    RoleEnum role,

    @NotNull(message = "Date of birth is required")
    @JsonProperty("date_of_birth")
    LocalDate dateOfBirth
) {

  public static UserEntity toEntity(UserDto dto) {
    return UserEntity.builder()
        .id(dto.id())
        .name(dto.name())
        .username(dto.username())
        .password(dto.password())
        .dateOfBirth(LocalDate.now())
        .build();
  }

  public static UserDto fromEntity(UserEntity userEntity) {
    return UserDto.builder()
        .id(userEntity.getId())
        .name(userEntity.getName())
        .username(userEntity.getUsername())
        .password(null)
        .dateOfBirth(userEntity.getDateOfBirth())
        .build();
  }

  public static List<UserDto> fromEntityList(List<UserEntity> userEntities) {
    return userEntities.
        stream()
        .map(UserDto::fromEntity)
        .toList();
  }
}
