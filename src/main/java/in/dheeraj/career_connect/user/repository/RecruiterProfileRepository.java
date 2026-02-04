package in.dheeraj.career_connect.user.repository;

import in.dheeraj.career_connect.user.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Long> {
}
