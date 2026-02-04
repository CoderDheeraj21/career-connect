package in.dheeraj.career_connect.user.service;

import in.dheeraj.career_connect.user.dto.UserRegistrationRequest;
import in.dheeraj.career_connect.user.dto.UserResponse;
import in.dheeraj.career_connect.user.entity.User;
import in.dheeraj.career_connect.user.enums.UserRole;
import in.dheeraj.career_connect.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserResponse register(UserRegistrationRequest request) {

        userRepository.findByEmail(request.getEmail())
                .ifPresent(u -> {
                    throw new RuntimeException("Email already registered");
                });

        User user = new User();
        user.setFirst_name(request.getFirstName());
        user.setLast_name(request.getLastName());
        user.setEmail(request.getEmail());
        user.setUserRole(
                request.getUserRole() != null ? request.getUserRole() : UserRole.ROLE_USER
        );
        user.setPassword(passwordEncoder.encode(request.getPassword()));

        User savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getFirst_name(),
                savedUser.getLast_name(),
                savedUser.getEmail(),
                savedUser.getUserRole().name()
        );
    }
}
