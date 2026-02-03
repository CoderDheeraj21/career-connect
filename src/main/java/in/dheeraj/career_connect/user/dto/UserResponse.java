package in.dheeraj.career_connect.user.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponse {

    private Integer id;
    private String firstName;
    private String lastName;
    private String email;
    private String role;
}
