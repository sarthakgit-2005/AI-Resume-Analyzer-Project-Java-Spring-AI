package org.airesume.resumeservice.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter

public class parserResponseDTO {
    private Set<String >skills;     //does not allow duplicate skills
    private List<String> education; //order matters for education(current/Latest), projects(Latest), Experience(Latest)
    private List<String> projects;
    private List<String> experience;
}
