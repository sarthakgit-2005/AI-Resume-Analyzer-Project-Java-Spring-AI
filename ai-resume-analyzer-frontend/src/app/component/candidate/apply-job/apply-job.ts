import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { CommonModule} from '@angular/common'; 
import { ActivatedRoute, Router } from '@angular/router';
import { ResumeService } from '../../../core/services/resume.service';

@Component({
  selector: 'app-apply-job',
  imports: [FormsModule, CommonModule],
  standalone:true,
  templateUrl: './apply-job.html',
  styleUrl: './apply-job.css',
})
export class ApplyJob {

  candidate={
    candidateName:'',
    candidateEmail:'',
    phone:'',
    jobId:0
  }
  selectedFile!:File;

  constructor(private activatedRoute:ActivatedRoute, private router:Router, private resumeService:ResumeService){}

  ngOnInit(){       //check this
    this.candidate.jobId=Number(this.activatedRoute.snapshot.paramMap.get('jobId'));
  }

  //understand this
  onFileSelected(event:any):void{
    this.selectedFile=event.target.files[0];
  }
submitApplication(): void {

  const formData = new FormData();

  formData.append(
    'candidateName',
    this.candidate.candidateName
  );

  formData.append(
    'candidateEmail',
    this.candidate.candidateEmail
  );

  formData.append(
    'phone',
    this.candidate.phone
  );

  formData.append(
    'jobId',
    this.candidate.jobId.toString()
  );

  formData.append(
    'file',
    this.selectedFile
  );

  this.resumeService
    .uploadResume(formData)
    .subscribe({

      next: (response) => {

        alert(
          'Application Submitted Successfully'
        );

        this.router.navigate(
          ['/candidate-jobs']
        );

      },

      error: (error) => {

        console.error(error);

        alert(
          'You Already Applied for this job'
        );

      }

    });

}
goBack(): void {
  this.router.navigate(['/candidate-jobs']);
}
}
