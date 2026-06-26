package org.airesume.aiservice.util;

import java.util.*;

public class ScoreCalculatorUtil {

    // Convert comma separated string into List
    /*
    public static List<String> convertStringToList(String data)
    {
        if(data == null || data.isBlank())
        {
            return new ArrayList<>();
        }

        String[] arr = data.split(",");

        Set<String> cleanedSkills = new HashSet<>();

        for(String s : arr)
        {
            String skill = s.toLowerCase()
                    .trim()
                    .replace("\n", "")
                    .replace("\r", "")
                    .replaceAll("\\(.*?\\)", "")                // removes (PostgreSQL)
                    .replaceAll("[^a-zA-Z0-9+# ]", "")          // removes symbols
                    .replaceAll("\\s+", " ");                   // multiple spaces -> one space

            if(!skill.isBlank())
            {
                cleanedSkills.add(skill);
            }
        }

        return new ArrayList<>(cleanedSkills);
    }
    */
    public static List<String> convertStringToList(String data)
    {
        if(data == null || data.isBlank())
        {
            return new ArrayList<>();
        }

        String[] arr = data.split(",");

        Set<String> cleanedSkills = new HashSet<>();

        for(String s : arr)
        {
            String skill = normalizeSkill(s);

            if(!skill.isBlank())
            {
                cleanedSkills.add(skill);
            }
        }

        return new ArrayList<>(cleanedSkills);
    }
    // Find matched skills
    public static List<String> getMatchedSkills(
            List<String> resumeSkills,
            List<String> jobSkills)
    {
        Set<String> matchedSkills = new HashSet<>();

        Set<String> normalizedResumeSkills =
                new HashSet<>(resumeSkills);

        for(String jobSkill : jobSkills)
        {
            if(normalizedResumeSkills.contains(jobSkill))
            {
                matchedSkills.add(jobSkill);
            }
        }

        return new ArrayList<>(matchedSkills);
    }
    // Find missing skills
    /*
    public static List<String> getMissingSkills(List<String> resumeSkills, List<String> jobSkills)
    {
        Set<String> missingSkills = new HashSet<>();
        for(String jobSkill : jobSkills)
        {
            boolean found = false;
            for(String resumeSkill : resumeSkills)
            {
                if(resumeSkill.contains(jobSkill) || jobSkill.contains(resumeSkill))
                {
                    found = true;
                    break;
                }
            }

            if(!found)
            {
                missingSkills.add(jobSkill);
            }
        }
        return new ArrayList<>(missingSkills);
    }
    */
    public static List<String> getMissingSkills(
            List<String> resumeSkills,
            List<String> jobSkills) {
        Set<String> missingSkills = new HashSet<>();

        Set<String> normalizedResumeSkills =
                new HashSet<>(resumeSkills);

        for (String jobSkill : jobSkills) {
            if (!normalizedResumeSkills.contains(jobSkill)) {
                missingSkills.add(jobSkill);
            }
        }

        return new ArrayList<>(missingSkills);
    }

    // Calculate ATS Score
    public static double calculateAtsScore(List<String> matchedSkills,
                                           List<String> jobSkills)
    {
        if(jobSkills == null || jobSkills.isEmpty())
        {
            return 0.0;
        }

        double score =
                ((double) matchedSkills.size() / jobSkills.size()) * 100;

        return Math.round(score * 100.0) / 100.0;
    }

    // Generate recommendation
    public static String generateRecommendation(double score)
    {
        if(score >= 80)
        {
            return "Highly Suitable";
        }
        else if(score >= 60)
        {
            return "Moderately Suitable";
        }
        else
        {
            return "Not Suitable";
        }
    }
    public static String normalizeSkill(String skill)
    {
        return skill.toLowerCase()
                .trim()
                .replace("\n", " ")
                .replace("\r", " ")
                .replace("/", " ")
                .replace("-", " ")
                .replaceAll("\\(.*?\\)", " ")
                .replaceAll("[^a-zA-Z0-9+#. ]", "")
                .replaceAll("\\s+", " ")
                .trim();
    }
}