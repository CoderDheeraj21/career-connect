package in.dheeraj.career_connect.service;

import in.dheeraj.career_connect.entity.RecruiterProfile;
import in.dheeraj.career_connect.repository.RecruiterProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RecruiterProfileService {

    private final RecruiterProfileRepository recruiterProfileRepository;

    public String createRecruiterProfile(RecruiterProfile recruiterProfile){

        recruiterProfileRepository.save(recruiterProfile);

        return "Recruiter Profile Created Successfully";

    }
}
