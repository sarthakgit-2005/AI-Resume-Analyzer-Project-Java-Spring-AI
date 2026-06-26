package org.airesume.resumeservice.controller;

//import org.springframework.stereotype.Controller;
import org.airesume.resumeservice.dto.resumeDTO;
import org.airesume.resumeservice.dto.resumeListDTO;
import org.airesume.resumeservice.repository.resumeRepository;
import org.airesume.resumeservice.service.resumeService;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.airesume.resumeservice.model.resume;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import org.airesume.resumeservice.dto.resumeResponseDTO;

@RestController
@RequestMapping("/api/resumes")
class resumeController {
    private final resumeService service;

    public resumeController(resumeService service) {
        this.service = service;
    }

    @PostMapping(value="/upload", consumes= MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<resumeResponseDTO> uploadResume(@ModelAttribute resumeDTO dto, @RequestParam("file") MultipartFile file) throws IOException
    {
        resumeResponseDTO savedResume=service.uploadResume(dto, file);
        return ResponseEntity.ok(savedResume);
    }
    @GetMapping("/{id}")
    public ResponseEntity<resume> getResumeById(@PathVariable Long id)
    {
        resume existingResume=service.getResumeById(id);
        return new ResponseEntity<>(existingResume, HttpStatus.OK);
    }
    //Get Resumes By JobId
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<resumeListDTO>> getResumeByJobId(@PathVariable Long jobId){
        List<resumeListDTO> resumeList=service.getResumeByJobId(jobId);
        return new ResponseEntity<>(resumeList, HttpStatus.OK);
    }

    //getAllResumes Initially
    @GetMapping
    public ResponseEntity<List<resumeListDTO>> getAllResumes()
    {
            List<resumeListDTO> resumeList=service.getAllResumes();
            return new ResponseEntity<>(resumeList, HttpStatus.OK);
    }

    //view resume without downloading it
    @GetMapping("/view/{id}")
    public ResponseEntity<Resource> viewResume(@PathVariable Long id)
    {
        File file = service.downloadResume(id);
        Resource resource = new FileSystemResource(file);
        return ResponseEntity.ok().contentType(MediaType.APPLICATION_PDF).body(resource);
    }
    //Delete Resume
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteResumeById(@PathVariable Long id){
        service.deleteResume(id);
        return new ResponseEntity<>("Resume deleted successfully", HttpStatus.OK);
    }

    //IMPP

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> downloadResume(@PathVariable Long id)
    {
        File file = service.downloadResume(id);
        Resource resource = new FileSystemResource(file);
        //convert physical file in to spring resource object
        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=" + file.getName())
                .contentType(MediaType.APPLICATION_PDF).body(resource);
    }
}
/*
Resource is a spring framework interface which is used to represent pdf files, images text data
FileResourceSystem is a class that implements resource file stored in system/disk
provided by spring-core
it automatically comes with spring-boot-starter-web no extra dependency required
 */
