package org.airesume.resumeparserservice.dto;

import java.util.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class parserResponseDTO {
    private  List<String> skills;
    private  List<String> education;
    private  List<String> projects;
    private  List<String> experience;
}
