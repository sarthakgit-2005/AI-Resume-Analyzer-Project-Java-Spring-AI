package org.airesume.aiservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

//used when ai-service directly communicates with resume service
//resume-service returns the data in the form of resumeResponseDTO
public class resumeResponseDTO {
    private String candidateName;
    private String candidateEmail;
    private  String phone;
    private Long jobId;
    private boolean aiProcessed;
    private String uploadedAt;

    //don't use Collection as the data is stored in the form of "Java, MVC, Microservices" like this not
    // ["Java", "MVC", "Microservices"] and the database columns are TEXT Datatype
    private String skills;
    private String education;
    private String experience;
    private String projects;

    private double ai_score;
}
