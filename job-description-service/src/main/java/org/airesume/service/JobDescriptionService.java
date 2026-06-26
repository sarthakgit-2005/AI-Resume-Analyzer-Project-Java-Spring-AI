package org.airesume.service;

import org.airesume.dto.jobdescriptiondto;
import org.airesume.entity.jobdescription;
import org.airesume.repository.JobDescriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobDescriptionService {
        private final JobDescriptionRepository jobdescriptionRepository;

    public JobDescriptionService(JobDescriptionRepository jobdescriptionRepository) {
        this.jobdescriptionRepository = jobdescriptionRepository;
    }
    //create Job
    public jobdescription saveJob(jobdescriptiondto dto)
    {
        boolean exists=jobdescriptionRepository.existsByCompanyNameAndJobTitleAndLocation(dto.getCompanyName(), dto.getJobTitle(), dto.getLocation());
        if(exists)
                throw new RuntimeException("Job is already present");
        jobdescription jd=new jobdescription();
        mapDtoToEntity(dto, jd);
        return jobdescriptionRepository.save(jd);
    }
    //delete Job
    public void deleteJob(Long id){
        jobdescription jd=jobdescriptionRepository.findById(id).orElseThrow(()->new RuntimeException("Job not found"));
        jobdescriptionRepository.delete(jd);
    }
    //updateJob
    public jobdescription updateJob(Long id, jobdescriptiondto dto)
    {
     jobdescription description=jobdescriptionRepository.findById(id).orElseThrow(()-> new RuntimeException("Job with id" + " "+ id + " "+ "cant found"));
     mapDtoToEntity(dto, description);
     return jobdescriptionRepository.save(description);
    }
    //getALLJobs
    public List<jobdescription> getAllJobs(){
        List<jobdescription> jobs= jobdescriptionRepository.findAll();
        if(jobs.isEmpty())
                throw new RuntimeException("No Jobs Found");
        return jobs;
    }
    //Custom/Derived Methods

    //Get Jobs By Company
   public  List<jobdescription> getJobsByCompany(String companyName)
    {
       List<jobdescription> jobs=jobdescriptionRepository.findByCompanyName(companyName);
       if(jobs.isEmpty())
           throw new RuntimeException("Jobs For Company" + " " + companyName + " " + " not found");
        return jobs;
    }
    //Get Jobs By Location
    public List<jobdescription> getJobsByLocation(String location)
    {
        List<jobdescription> jobs=jobdescriptionRepository.findByLocation(location);
        if(jobs.isEmpty())
            throw new RuntimeException("No Jobs for Location" + " " + location + " " + "found");
        return jobs;
    }
    //getJobsByJobTitle
    public List<jobdescription> getJobsByJobTitle(String jobTtile)
    {
        List<jobdescription> jobs=jobdescriptionRepository.findByJobTitleContainingIgnoreCase(jobTtile);
        if(jobs.isEmpty())
                throw new RuntimeException("No jobs for the job title" + " " + jobTtile + " " + "found");
        return jobs;
    }


    //DTO->Entity Mapping
    private void mapDtoToEntity(
            jobdescriptiondto dto,
            jobdescription entity) {

        entity.setCompanyName(
                dto.getCompanyName());
        //entity.setRecruiterName(
                //dto.getRecruiterName());
        entity.setDescription(
                dto.getDescription());
        entity.setAdditionalInfo(
                dto.getAdditionalInfo());
        entity.setJobTitle(
                dto.getJobTitle());
        entity.setExperience(
                dto.getExperience());
        entity.setLocation(
                dto.getLocation());
    }
    //Get Job By Id
    public jobdescription getJobById(Long id){
        return jobdescriptionRepository.findById(id).orElseThrow(()-> new RuntimeException("Jon Not Found"));
    }

}
