package in.dheeraj.career_connect.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateJobRequest {

    @NotNull(message = "Recruiter id is required")
    private Long recruiter_id;

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Location is required")
    private String location;

    @NotBlank(message = "Description is required")
    private String description;

    @NotEmpty(message = "At least one skill is required")
    private List<String> skills;
}
