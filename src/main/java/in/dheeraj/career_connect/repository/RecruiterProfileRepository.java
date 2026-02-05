package in.dheeraj.career_connect.repository;

import in.dheeraj.career_connect.entity.RecruiterProfile;
import in.dheeraj.career_connect.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Long> {

    Optional<RecruiterProfile> findByUser(User user);
}
