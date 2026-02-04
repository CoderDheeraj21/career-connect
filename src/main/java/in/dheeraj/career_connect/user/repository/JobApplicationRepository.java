package in.dheeraj.career_connect.user.repository;

import in.dheeraj.career_connect.user.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}
