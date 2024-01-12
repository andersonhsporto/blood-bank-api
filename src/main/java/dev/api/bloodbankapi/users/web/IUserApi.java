package dev.api.bloodbankapi.users.web;

import dev.api.bloodbankapi.base.ApiUtil;
import dev.api.bloodbankapi.users.domain.UserDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Iterator;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.request.NativeWebRequest;

@Validated
@Tag(name = "user", description = "Operations pertaining to user")
public interface IUserApi {

  default Optional<NativeWebRequest> getRequest() {
    return Optional.empty();
  }

  @Operation(
      operationId = "getUserById",
      summary = "Find user by ID",
      description = "Returns a single user given their ID",
      tags = {"user"},
      responses = {@ApiResponse(
          responseCode = "200",
          description = "Successful operation",
          content = {@Content(
              mediaType = "application/json",
              schema = @Schema(
                  implementation = UserDto.class
              )
          )}
      ), @ApiResponse(
          responseCode = "404",
          description = "User not found"
      )}
  )
  @RequestMapping(
      method = {RequestMethod.GET},
      value = {"/user/{id}"},
      produces = {"application/json"}
  )
  default ResponseEntity<UserDto> _getBeerById(
      @Parameter(name = "id", description = "Id of user in database", required = true, in = ParameterIn.PATH) @PathVariable("id") Long id) {
    return this.getUserById(id);
  }

  // Function to override
  default ResponseEntity<UserDto> getUserById(Long id) {
    this.getRequest().ifPresent((request) -> {
      Iterator var1 = MediaType.parseMediaTypes(request.getHeader("Accept")).iterator();

      while (var1.hasNext()) {
        MediaType mediaType = (MediaType) var1.next();
        String exampleString;
        if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
          exampleString = "{ \"id\" : \"id\", \"name\" : \"name\", \"username\" : \"username\", \"role\" : \"role\", \"date_of_birth\" : \"date_of_birth\" }";
          ApiUtil.setExampleResponse(request, "application/json", exampleString);
          break;
        }
      }

    });
    return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
  }

}
