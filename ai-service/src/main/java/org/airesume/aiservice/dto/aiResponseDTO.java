package org.airesume.aiservice.dto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@NoArgsConstructor

public class aiResponseDTO {
    private Long resumeId;
    private Long jobId;
    private double atsScore;
    private List<String> matchedSkills;
    private List<String> missingSkills;
    private String recommendation;
}
