package se.lexicon.g51todoapi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.lexicon.g51todoapi.domain.dto.RoleDTOView;
import se.lexicon.g51todoapi.domain.dto.UserDTOForm;
import se.lexicon.g51todoapi.domain.dto.UserDTOView;
import se.lexicon.g51todoapi.service.UserService;

import java.util.HashSet;
import java.util.Set;

@RequestMapping("/api/v1/users")
@RestController
@Validated
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public ResponseEntity<UserDTOView> doGetUserByEmail(
            @RequestParam
            @NotEmpty
            @NotNull
            @Pattern(regexp = "^[A-Za-z0-9+_.-]+@(.+)$", message = "Invalid email format")
            String email) {
        System.out.println(">>>>>>> getUserByEmail has been executed.");
        UserDTOView responseBody = userService.getByEmail(email);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    //Swagger UI annotations
    @Operation(summary = "Register a new user", description = "Creates a new user in the system")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input")
    })
    @PostMapping
    public ResponseEntity<UserDTOView> doRegister(@RequestBody @Valid UserDTOForm userDTOForm) {
        System.out.println("DTO Form: " + userDTOForm);
        UserDTOView responseBody = userService.register(userDTOForm);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    @PutMapping("/disable")
    public ResponseEntity<Void> doDisableUserByEmail(@RequestParam @NotBlank @Email String email) {
        userService.disableByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/enable")
    public ResponseEntity<Void> doEnableUserByEmail(@RequestParam String email) {
        userService.enableByEmail(email);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
