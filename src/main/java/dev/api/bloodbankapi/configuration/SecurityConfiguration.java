package dev.api.bloodbankapi.configuration;

import dev.api.bloodbankapi.users.base.RoleEnum;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfiguration {

  private static final String[] ADMIN_MATCHERS = {
      "/admin-sign-up",
  };

  private static final String[] USER_MATCHERS = {
      "/user/{id}/bloodtype/{bloodType}"
  };

  private static final String[] DONOR_MATCHERS = {
      "/sign-up"
  };

  private static final String[] PUBLIC_MATCHERS = {
      "/auth"
  };

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            authCfg -> {
              authCfg.requestMatchers(ADMIN_MATCHERS).hasAuthority(RoleEnum.ADMIN.name());
              authCfg.requestMatchers(USER_MATCHERS).hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.USER.name());
              authCfg.requestMatchers(DONOR_MATCHERS).hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.USER.name(), RoleEnum.DONOR.name());
              authCfg.requestMatchers(PUBLIC_MATCHERS).permitAll();
              authCfg.anyRequest().authenticated();
            })
        .sessionManagement(
            s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }


}
