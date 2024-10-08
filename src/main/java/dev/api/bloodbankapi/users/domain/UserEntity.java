package dev.api.bloodbankapi.users.domain;


import dev.api.bloodbankapi.auth.domain.TokenEntity;
import dev.api.bloodbankapi.base.BaseEntity;
import dev.api.bloodbankapi.users.base.BloodTypeEnum;
import dev.api.bloodbankapi.users.base.RoleEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
public class UserEntity extends BaseEntity implements UserDetails {

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "username", unique = true, nullable = false)
  private String username;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "date_of_birth", nullable = true)
  private LocalDate dateOfBirth;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  @Enumerated(EnumType.STRING)
  @Column(name = "role", nullable = true)
  private RoleEnum role;

  @Enumerated(EnumType.ORDINAL)
  @Column(name = "blood_type", nullable = true)
  private BloodTypeEnum bloodType;

  @OneToMany(mappedBy = "user")
  private List<TokenEntity> tokens;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    Collection<? extends GrantedAuthority> authorities = List.of(() -> role.name());

    return authorities;
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }

  @Override
  public String toString() {
    return "UserEntity{" +
        "name='" + name + '\'' +
        ", username='" + username + '\'' +
        ", password='" + password + '\'' +
        ", dateOfBirth=" + dateOfBirth +
        ", email='" + email + '\'' +
        ", role=" + role +
        '}';
  }
}