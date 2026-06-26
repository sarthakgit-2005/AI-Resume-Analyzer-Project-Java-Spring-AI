package org.airesume.resumeservice.repository;

import org.airesume.resumeservice.model.resume;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface resumeRepository extends JpaRepository<resume, Long> {

    //Get All Resumes For a Particular Job
    List<resume> findByJobId(Long jobId);
    //Check Duplicate Application
    Optional<resume> findByCandidateEmailAndJobId(String candidateEmail, Long jobId);
    //Pending AI Analysis Resume
    List<resume> findByAiProcessed(boolean aiProcessed);
}
