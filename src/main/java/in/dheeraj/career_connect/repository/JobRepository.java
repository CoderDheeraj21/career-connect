package in.dheeraj.career_connect.repository;

import in.dheeraj.career_connect.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job, Long> {
}
