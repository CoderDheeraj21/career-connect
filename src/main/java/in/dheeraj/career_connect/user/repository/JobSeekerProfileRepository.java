package in.dheeraj.career_connect.user.repository;

import in.dheeraj.career_connect.user.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobSeekerProfileRepository extends JpaRepository<JobSeekerProfile, Long> {
}
