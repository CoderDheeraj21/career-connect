package in.dheeraj.career_connect.user.repository;

import in.dheeraj.career_connect.user.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
