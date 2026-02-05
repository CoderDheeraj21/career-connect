package in.dheeraj.career_connect.controller;

import in.dheeraj.career_connect.dto.CreateJobRequest;
import in.dheeraj.career_connect.service.JobService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JobController {

    private final JobService jobService;

    @PostMapping("/create-job")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<String> createJob(@Valid @RequestBody CreateJobRequest jobRequest){
        String job = jobService.createJob(jobRequest);
        return ResponseEntity.ok(job);
    }
}
