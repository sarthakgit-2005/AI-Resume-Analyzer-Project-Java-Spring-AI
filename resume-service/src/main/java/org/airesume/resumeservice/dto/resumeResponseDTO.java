package org.airesume.resumeservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class resumeResponseDTO {

    private Long id;
    private String candidateName;
    private String candidateEmail;
    private String phone;
    private Long jobId;
    private boolean aiProcessed;
    private String uploadedAt;
}
