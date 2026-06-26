package org.airesume.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class jobdescriptiondto {
    private String companyName;
    //private String recruiterName;
    private String description;
    private String additionalInfo;
    private String jobTitle;
    private int experience;
    private String location;
}
