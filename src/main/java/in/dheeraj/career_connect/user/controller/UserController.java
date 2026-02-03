package in.dheeraj.career_connect.user.controller;

import in.dheeraj.career_connect.user.dto.UserRegistrationRequest;
import in.dheeraj.career_connect.user.dto.UserResponse;
import in.dheeraj.career_connect.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;  // the final keyword here used by lombok describes that class cannot exist without this dependency and it is mandatory and it should be provided and construction time

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(
            @RequestBody UserRegistrationRequest request
    ) {
        UserResponse response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/admin-endpoint")
    @PreAuthorize("hasRole('ADMIN')") // when you use hasRole() spring internal checks for ROLE_ADMIN so define the enum correctly
    public ResponseEntity<String> endpointForAdmin(){
        return new ResponseEntity<>("Admin Endpoint", HttpStatus.OK);
    }

    @GetMapping("/recruiter-endpoint")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<String> endpointForRecruiter(){
        return new ResponseEntity<>("Recruiter Endpoint", HttpStatus.OK);
    }

    @GetMapping("/user-endpoint")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> endpointForUser(){
        return new ResponseEntity<>("User Endpoint", HttpStatus.OK);
    }
}
