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

  private static final String[] PUBLIC_MATCHERS = {
      "/public/**"
  };

  private final JwtAuthenticationFilter jwtAuthenticationFilter;

  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
        .csrf(csrf -> csrf.disable())
        .authorizeHttpRequests(
            authCfg -> {
              authCfg.requestMatchers("/auth").permitAll();
              authCfg.requestMatchers("/sign-up").hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.USER.name(), RoleEnum.DONOR.name());
              authCfg.requestMatchers("/admin-sign-up").hasAuthority(RoleEnum.ADMIN.name());
              authCfg.requestMatchers("/user/**").hasAnyAuthority(RoleEnum.ADMIN.name(), RoleEnum.USER.name(), RoleEnum.DONOR.name());
              authCfg.anyRequest().authenticated();
            })
        .sessionManagement(
            s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        )
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .build();
  }


}
