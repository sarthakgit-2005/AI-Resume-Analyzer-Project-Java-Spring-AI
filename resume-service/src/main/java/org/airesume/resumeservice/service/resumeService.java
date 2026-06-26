package org.airesume.resumeservice.service;

import org.airesume.resumeservice.dto.*;
import org.airesume.resumeservice.repository.resumeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.airesume.resumeservice.util.pdfExtractorUtil;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.airesume.resumeservice.model.resume;

@Service
public class resumeService {
    private final resumeRepository resumeRepository;
    private final pdfExtractorUtil pdfExtractorUtil;
    private final RestTemplate restTemplate;

    @Value("${file.upload-dir}")
    private String uploadDir;

    //It is a springboot annotation used for injecting a value from configuration properties into java variable
    //it says look for a property named as file.upload-dir inside the application.properties or yaml

    public resumeService(resumeRepository resumeRepository, pdfExtractorUtil pdfExtractorUtil, RestTemplate restTemplate) {
        this.resumeRepository = resumeRepository;
        this.pdfExtractorUtil = pdfExtractorUtil;
        this.restTemplate=restTemplate;
    }
    //Upload Resume
    public resumeResponseDTO uploadResume(resumeDTO resumeDTO, MultipartFile file) throws IOException {
        //check if file is empty
        if (file.isEmpty()) {
            throw new RuntimeException("Resume File is Empty");
        }
        //check if resume is of type pdf or not
        if (!file.getContentType().equalsIgnoreCase("application/pdf")) {
            throw new RuntimeException("Only PDF Files are supported");
        }
        //check duplicate application
        boolean applied = resumeRepository.findByCandidateEmailAndJobId(resumeDTO.getCandidateEmail(), resumeDTO.getJobId()).isPresent();
        if (applied) {
            throw new RuntimeException("Candidate already applied for this job post");
        }
        //create upload directory
        File directory = new File(uploadDir);
        if (!directory.exists()) {
            boolean created = directory.mkdirs();
            System.out.println("Folder Created = " + created);
        } else {
            System.out.println("Folder Already Exists");
        }
        //generate unique file name
        String uniqueFileName = UUID.randomUUID() + "_" + file.getOriginalFilename();
        //File Path
        Path filePath = Paths.get(uploadDir, uniqueFileName);
        //save file
        Files.copy(file.getInputStream(), filePath);
        //Extract text from pdf
        String extractedText = pdfExtractorUtil.extractedText(filePath.toString());

        //clean Text method before saving it in database
        extractedText=cleanText(extractedText);

        //Parser Request DTO
        parserRequestDTO requestDTO=new parserRequestDTO();
        requestDTO.setResumeText(extractedText);
        parserResponseDTO parserresponseDTO = restTemplate.postForObject("http://localhost:8093/api/parser/parse",requestDTO,
                                                        parserResponseDTO.class);

        if(parserresponseDTO == null){
            throw new RuntimeException("Parser Service Failed");
        }

        //model
        resume resume = new resume();

        mapDtoToEntity(resumeDTO, resume);

        resume.setFileName(
                uniqueFileName);

        resume.setFileType(
                file.getContentType());

        resume.setFilePath(
                filePath.toString());

            //IMPP

            resume.setSkills(parserresponseDTO.getSkills()!=null ?
                        String.join(",", parserresponseDTO.getSkills()):null);
            resume.setEducation(parserresponseDTO.getEducation()!=null ?
                    String.join(",",parserresponseDTO.getEducation()):null);
            resume.setExperience(parserresponseDTO.getExperience()!=null
                                ? String.join(",",parserresponseDTO.getExperience()):null);
            resume.setProjects(parserresponseDTO.getProjects()!=null ?
                                String.join(",",parserresponseDTO.getProjects()):null);

        //resume.setAi_score(0.0);

        // SAVE TO DATABASE

        resume savedResume=resumeRepository.save(resume);
        resumeResponseDTO responseDTO = new resumeResponseDTO();

        responseDTO.setId(savedResume.getId());
        responseDTO.setCandidateName(savedResume.getCandidateName());
        responseDTO.setCandidateEmail(savedResume.getCandidateEmail());
        responseDTO.setPhone(savedResume.getPhone());
        responseDTO.setJobId(savedResume.getJobId());
        responseDTO.setAiProcessed(savedResume.isAiProcessed());
        responseDTO.setUploadedAt(savedResume.getUploadedAt().toString());  //check kr

        return responseDTO;
    }

    //Resume By ID(Resume)
    public resume getResumeById(Long id) {
        return resumeRepository.findById(id).orElseThrow(() -> new RuntimeException("Resume Not Found"));
    }

    //Resume By Job ID
    public List<resumeListDTO> getResumeByJobId(Long jobId) {

        List<resume> resumes = resumeRepository.findByJobId(jobId);
        if (resumes.isEmpty()) {
            throw new RuntimeException("No Resume Found For Job Id " + jobId);
        }
        List<resumeListDTO> result = new ArrayList<>();
        for (resume r : resumes) {
            resumeListDTO dto = new resumeListDTO();

            dto.setResumeId(r.getId());
            dto.setJobId(r.getJobId());
            dto.setCandidateName(r.getCandidateName());

            // Skills preview (simple)
            if (r.getSkills() != null) {
                String[] skills = r.getSkills().split(",");

                if (skills.length > 2) {
                    dto.setSkillsPreview(skills[0] + ", " + skills[1] + " +more");
                } else {
                    dto.setSkillsPreview(r.getSkills());
                }
            }

            // Simple experience (temporary)
            dto.setExperienceSummary(r.getExperience());
            result.add(dto);
        }
        return result;
    }
    //delete Resume
    public void deleteResume(Long id)
    {
        resume returnedResume=getResumeById(id);
        //Delete From Storage
        File file=new File(returnedResume.getFilePath());
        if(file.exists()){
            file.delete();
        }
        //Delete Database Record
        resumeRepository.delete(returnedResume);
    }
    //Download Resume Which is uploaded by user
    public File downloadResume(Long id){
        resume existingResume = getResumeById(id);
        File file = new File(existingResume.getFilePath());
        if(!file.exists()){
            throw new RuntimeException("Resume file not found");
        }
        return file;
    }

    //getALlResumes
    public List<resumeListDTO> getAllResumes(){
        List<resume> resumes=resumeRepository.findAll();
        List<resumeListDTO> dtoList=new ArrayList<>();
        for(resume r:resumes){
            resumeListDTO dto=new resumeListDTO();
            dto.setResumeId(r.getId());
            dto.setJobId(r.getJobId());
            dto.setCandidateName(r.getCandidateName());
            dto.setSkillsPreview(r.getSkills());
            dto.setExperienceSummary(r.getExperience());

            dtoList.add(dto);
        }
        return dtoList;
    }

    private void mapDtoToEntity(resumeDTO dto,resume resume)
    {
        resume.setCandidateName(dto.getCandidateName());
        resume.setCandidateEmail(dto.getCandidateEmail());
        resume.setPhone(dto.getPhone());
        resume.setJobId(dto.getJobId());
    }
    //IMPP Code
    //Clean Text Method which cleans the data like bullet point, special symbols before saving it in database
    private String cleanText(String text){
        if(text == null)
        {
            return null;
        }
        return text.replace("◾", "")
                .replace("•", "")
                .replace("\r", " ")
                .replace("\n", " ")
                .replaceAll("\\s+", " ")
                .trim();
        }
}
