package org.airesume.resumeparserservice.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class parserUtil {

    // Normalize single skill
    public static String normalizeSkill(String skill)
    {
        if(skill == null)
        {
            return "";
        }

        return skill.toLowerCase()
                .trim()
                .replace("\n", " ")
                .replace("\r", " ")
                .replace("/", " ")
                .replace("-", " ")
                .replace("&", " ")
                .replaceAll("\\(.*?\\)", " ")
                .replaceAll("[^a-zA-Z0-9+#. ]", "")
                .replaceAll("\\s+", " ")
                .trim();
    }

    // Clean complete skill list
    public static List<String> cleanSkillList(List<String> skills)
    {
        if(skills == null || skills.isEmpty())
        {
            return new ArrayList<>();
        }

        Set<String> cleanedSkills = new HashSet<>();

        for(String skill : skills)
        {
            String normalizedSkill = normalizeSkill(skill);

            if(!normalizedSkill.isBlank())
            {
                cleanedSkills.add(normalizedSkill);
            }
        }

        return new ArrayList<>(cleanedSkills);
    }
}