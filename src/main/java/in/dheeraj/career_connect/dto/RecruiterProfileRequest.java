package in.dheeraj.career_connect.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RecruiterProfileRequest {

    @NotBlank(message = "Company name cannot be empty")
    private String companyName;

    @NotBlank(message = "Designation is required")
    private String designation;
}
