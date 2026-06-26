package org.airesume.repository;
import org.airesume.entity.jobdescription;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface JobDescriptionRepository extends JpaRepository<jobdescription, Long> {
    //Find Jobs By Company
    List<jobdescription> findByCompanyName(String companyName);
    //findByLocation
    List<jobdescription> findByLocation(String location);
    //findByJobTitle
    List<jobdescription> findByJobTitleContainingIgnoreCase(String jobTitle);
    //job duplicates check
    boolean existsByCompanyNameAndJobTitleAndLocation(String companyName, String jobTitle, String location);
}
