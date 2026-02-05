package in.dheeraj.career_connect.service;

import in.dheeraj.career_connect.dto.CreateJobRequest;
import in.dheeraj.career_connect.entity.Job;
import in.dheeraj.career_connect.entity.RecruiterProfile;
import in.dheeraj.career_connect.entity.User;
import in.dheeraj.career_connect.enums.JobStatus;
import in.dheeraj.career_connect.repository.JobRepository;
import in.dheeraj.career_connect.repository.RecruiterProfileRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class JobService {

    private final JobRepository jobRepository;

    private final RecruiterProfileRepository recruiterProfileRepository;

    public String createJob(CreateJobRequest jobRequest){
        User currentUser = getCurrentUser();

        RecruiterProfile recruiterProfile =
                recruiterProfileRepository.findByUser(currentUser)
                        .orElseThrow(() ->
                                new IllegalStateException("Recruiter profile not found")
                        );


        Job job = new Job();
        job.setTitle(jobRequest.getTitle());
        job.setDescription(jobRequest.getDescription());
        job.setSkills(jobRequest.getSkills());
        job.setLocation(jobRequest.getLocation());
        job.setCreatedAt(LocalDateTime.now());
        job.setStatus(JobStatus.OPEN);
        job.setRecruiterProfile(recruiterProfile);
        jobRepository.save(job);
        return "Job Created Successfully";
    }

    private User getCurrentUser(){
         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        assert authentication != null;
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

        assert userDetails != null;
        return userDetails.getUser();

    }


}
