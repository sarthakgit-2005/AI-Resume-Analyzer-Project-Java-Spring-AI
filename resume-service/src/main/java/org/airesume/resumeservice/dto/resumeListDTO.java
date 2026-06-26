package org.airesume.resumeservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class resumeListDTO {
    private Long resumeId;
    private Long jobId;
    private String candidateName;
    private String skillsPreview;
    private String experienceSummary;

}
