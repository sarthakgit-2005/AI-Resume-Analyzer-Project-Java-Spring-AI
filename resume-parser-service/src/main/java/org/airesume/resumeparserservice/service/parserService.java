/*package org.airesume.resumeparserservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.airesume.resumeparserservice.dto.parserResponseDTO;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class parserService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    public parserService(ChatClient.Builder builder,
                         ObjectMapper objectMapper) {

        this.chatClient = builder.build();
        this.objectMapper = objectMapper;
    }

    public parserResponseDTO parseResume(String resumeText) throws Exception {

        String prompt = """
            Extract the following from the resume:

            1. Skills
            2. Education
            3. Projects
            4. Experience

            IMPORTANT:
            Return ONLY valid JSON.
            Do not write explanation text.
            Do not write 'Here is the JSON'.
            Do not use markdown.

            Example:

            {
              "skills":["Java","Spring Boot"],
              "education":["BSc Computer Science"],
              "projects":["AI Resume Analyzer"],
              "experience":["Fresher"]
            }

            Resume:
            """ + resumeText;

        String aiResponse = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        System.out.println("RAW AI RESPONSE:");
        System.out.println(aiResponse);

        // Remove markdown if present
        aiResponse = aiResponse.replace("```json", "").replace("```", "")
                .trim();

        // Extract only JSON object
        int start = aiResponse.indexOf("{");
        int end = aiResponse.lastIndexOf("}");

        if (start != -1 && end != -1) {
            aiResponse = aiResponse.substring(start, end + 1);              //check
        }

        System.out.println("CLEANED JSON:");
        System.out.println(aiResponse);

        return objectMapper.readValue(aiResponse, parserResponseDTO.class);
    }
}
*/

package org.airesume.resumeparserservice.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.airesume.resumeparserservice.dto.parserResponseDTO;
import org.airesume.resumeparserservice.util.parserUtil;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class parserService {

    private final ChatClient chatClient;
    private final ObjectMapper objectMapper;

    public parserService(ChatClient.Builder builder,
                         ObjectMapper objectMapper) {

        this.chatClient = builder.build();
        this.objectMapper = objectMapper;
    }

    public parserResponseDTO parseResume(String resumeText) throws Exception {

        String prompt = """
            Extract the following from the resume:

            1. Skills
            2. Education
            3. Projects
            4. Experience

            IMPORTANT:
            Return ONLY valid JSON.
            Do not write explanation text.
            Do not write 'Here is the JSON'.
            Do not use markdown.

            Rules for skills:
            1. Return skills individually
            2. Do not combine skills
            3. Split combined skills

            Example:
            "Git & GitHub" -> ["Git","GitHub"]
            "SQL (PostgreSQL)" -> ["SQL","PostgreSQL"]

            Example JSON:

            {
              "skills":["Java","Spring Boot"],
              "education":["BSc Computer Science"],
              "projects":["AI Resume Analyzer"],
              "experience":["Fresher"]
            }

            Resume:
            """ + resumeText;

        String aiResponse = chatClient.prompt()
                .user(prompt)
                .call()
                .content();

        System.out.println("RAW AI RESPONSE:");
        System.out.println(aiResponse);

        // Remove markdown if present
        aiResponse = aiResponse
                .replace("```json", "")
                .replace("```", "")
                .trim();

        // Extract only JSON object
        int start = aiResponse.indexOf("{");
        int end = aiResponse.lastIndexOf("}");

        if (start != -1 && end != -1)
        {
            aiResponse = aiResponse.substring(start, end + 1);
        }

        System.out.println("CLEANED JSON:");
        System.out.println(aiResponse);

        // Convert JSON -> DTO
        parserResponseDTO dto =
                objectMapper.readValue(aiResponse, parserResponseDTO.class);

        // CLEAN SKILLS
        List<String> cleanedSkills =
                parserUtil.cleanSkillList(dto.getSkills());

        dto.setSkills(cleanedSkills);

        return dto;
    }
}
