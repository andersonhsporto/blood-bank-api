package dev.api.bloodbankapi.auth.domain;

import dev.api.bloodbankapi.base.BaseEntity;
import dev.api.bloodbankapi.users.domain.UserEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.oauth2.core.OAuth2AccessToken.TokenType;

@Entity
@Table(name = "tokens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class TokenEntity extends BaseEntity {

  @Column(name = "token", unique = true)
  private String token;

  @Column(name = "token_type")
  private TokenType tokenType = TokenType.BEARER;

  @Column(name = "revoked")
  private boolean revoked;

  @Column(name = "expired")
  private boolean expired;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  public UserEntity user;

  public boolean isExpired() {
    return this.expired;
  }

  public boolean isRevoked() {
    return this.revoked;
  }

  public void expireToken() {
    this.expired = true;
  }

  public void revokeToken() {
    this.revoked = true;
  }
}
