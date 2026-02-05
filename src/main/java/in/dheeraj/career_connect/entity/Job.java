package in.dheeraj.career_connect.entity;


import in.dheeraj.career_connect.enums.JobStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Job {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "recruiter_id", nullable = false)
    private RecruiterProfile recruiterProfile;

    private String title;

    private String location;

    private String description;

    private List<String> skills;

    @Enumerated(EnumType.STRING)
    private JobStatus status;

    private LocalDateTime createdAt;

}
