package in.dheeraj.career_connect.controller;

import in.dheeraj.career_connect.entity.RecruiterProfile;
import in.dheeraj.career_connect.service.RecruiterProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RecruiterProfileController {

    private final RecruiterProfileService recruiterProfileService;

    @PostMapping("/recruiter/profile")
    @PreAuthorize("hasRole('RECRUITER')")
    public ResponseEntity<String> createRecruiterProfile(@RequestBody RecruiterProfile recruiterProfile){
        String message = recruiterProfileService.createRecruiterProfile(recruiterProfile);
        return ResponseEntity.ok(message);
    }
}
