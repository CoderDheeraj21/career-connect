package in.dheeraj.career_connect.repository;

import in.dheeraj.career_connect.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobApplicationRepository extends JpaRepository<JobApplication, Long> {
}
