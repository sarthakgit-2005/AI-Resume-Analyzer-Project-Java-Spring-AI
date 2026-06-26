package org.airesume.controller;

import org.airesume.service.JobDescriptionService;
import org.airesume.dto.jobdescriptiondto;
import org.airesume.entity.jobdescription;
import org.airesume.repository.JobDescriptionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//import org.springframework.web.bind.annotation.ResponseBody;


@RestController
@RequestMapping("/api/jobdesc")
public class jobdescriptioncontroller {

    private final JobDescriptionRepository repository;
    private final JobDescriptionService descriptionService;

    //Constructor Injection
    public jobdescriptioncontroller(JobDescriptionRepository repository, JobDescriptionService descriptionService) {
        this.repository = repository;
        this.descriptionService = descriptionService;
    }

    @PostMapping("/create")
    public ResponseEntity<jobdescription> createJob(@RequestBody jobdescriptiondto dto) {
        return new ResponseEntity<>(descriptionService.saveJob(dto), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<jobdescription> updateJob(@PathVariable Long id, @RequestBody jobdescriptiondto dto){
        jobdescription updatedJob=descriptionService.updateJob(id, dto);
        return new ResponseEntity<>(updatedJob, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public void  deleteJob(@PathVariable Long id){
        descriptionService.deleteJob(id);
    }
    //GetAllJobs
    @GetMapping("/all")
    public ResponseEntity<List<jobdescription>> getAllJobs(){
        List<jobdescription> jobs=descriptionService.getAllJobs();
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }

    //derived methods accessed from service to controller
    @GetMapping("/company/{companyName}")
    public ResponseEntity<List<jobdescription>> getJobsByCompanyName(@PathVariable  String companyName){
        List<jobdescription> jobs=descriptionService.getJobsByCompany(companyName);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
    @GetMapping("/location/{location}")
    public ResponseEntity<List<jobdescription>> getJobsByLocation(@PathVariable  String location)
    {
        List<jobdescription> jobs=descriptionService.getJobsByLocation(location);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
    @GetMapping("/jobTitle/{jobTitle}")
    public ResponseEntity<List<jobdescription>> getJobsByJobTitle(@PathVariable String jobTitle)
    {
        List<jobdescription> jobs=descriptionService.getJobsByJobTitle(jobTitle);
        return new ResponseEntity<>(jobs, HttpStatus.OK);
    }
    //Get Job By Id
    @GetMapping("/{id}")
    public ResponseEntity<jobdescription> getJobById(@PathVariable Long id){
        jobdescription job=descriptionService.getJobById(id);
        return new ResponseEntity<>(job,HttpStatus.OK);
    }
}
