package in.dheeraj.career_connect.user.entity;


import in.dheeraj.career_connect.user.enums.JobStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

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

    private JobStatus status;

    private LocalDateTime createdAt;

}
