package in.dheeraj.career_connect.user.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class RecruiterProfile {

    private Long id;

    @OneToOne
    @JoinColumn(name="user_id", nullable = false)
    private User user;

    private String companyName;

    private String designation;

}
