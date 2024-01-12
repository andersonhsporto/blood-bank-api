package dev.api.bloodbankapi.auth.web;

import com.nimbusds.openid.connect.sdk.AuthenticationResponse;
import dev.api.bloodbankapi.auth.domain.AuthDTO;
import dev.api.bloodbankapi.configuration.JwtService;
import java.io.IOException;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import jakarta.servlet.http.HttpServletResponse;
@AllArgsConstructor
@RestController
public class AuthController {

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  private final UserDetailsService userDetailsService;

  @PostMapping("/auth")
  public String createAuthenticationToken(@RequestBody AuthDTO authenticationDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException {
    try {
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.email(), authenticationDTO.password()));
    } catch (BadCredentialsException e) {
      throw new BadCredentialsException("Incorrect username or password!");
    } catch (DisabledException disabledException) {
      response.sendError(HttpServletResponse.SC_NOT_FOUND, "User is not activated");
      return null;
    }

    final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.email());

    return jwtService.generateToken(userDetails);

  }


}
