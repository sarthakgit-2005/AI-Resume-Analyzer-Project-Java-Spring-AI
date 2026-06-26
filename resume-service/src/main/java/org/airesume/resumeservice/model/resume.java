package org.airesume.resumeservice.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name="resume")
@NoArgsConstructor
@Getter
@Setter
public class resume {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    //candidate information
    private String candidateName;
    private String candidateEmail;
    private String phone;

    //Resume File Information
    private String fileName;
    private String fileType;
    private String filePath;

    //Extracted Text from resume
    //@Column(columnDefinition = "TEXT")
    //private String extractedText;

    //Applied For which JobId
    private Long jobId;

    //AI Analysis Status
    private boolean aiProcessed;

    //Upload Timestamp
    private LocalDateTime uploadedAt;

    @Column(columnDefinition = "TEXT")
    private String skills;

    @Column(columnDefinition = "TEXT")
    private String education;

    @Column(columnDefinition = "TEXT")
    private String projects;

    //@Column(columnDefinition = "TEXT")
    //private String certifications;

    @Column(columnDefinition = "TEXT")
    private String experience;

    //private double ai_score;

    @PrePersist
    //Automatically Executes before the Entity Insertion in Database
    public void prePersist() {
        this.uploadedAt = LocalDateTime.now();
        this.aiProcessed = false;
    }
}
