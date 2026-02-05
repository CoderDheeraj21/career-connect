package in.dheeraj.career_connect.entity;

import in.dheeraj.career_connect.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String first_name;

    private String last_name;

    @Enumerated(EnumType.STRING)
    private UserRole userRole;

    private String email;

    private String password;
}
