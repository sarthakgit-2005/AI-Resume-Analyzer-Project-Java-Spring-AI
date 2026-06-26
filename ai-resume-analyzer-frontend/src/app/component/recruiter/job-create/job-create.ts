import { Component } from '@angular/core';    //imports angular component decorator
import {JobModel} from '../../../core/models/job.model';
import {JobService} from '../../../core/services/job.service';
import {FormsModule} from '@angular/forms';

@Component({                        //component decorator: defines component metadata
  selector: 'app-job-create',     //used in html file
  imports: [FormsModule],
  standalone:true,
  templateUrl: './job-create.html',
  styleUrl: './job-create.css',
})
export class JobCreate {
  job:JobModel={companyName:'', jobTitle:'', description:'',additionalInfo:'',location:'',experience:0}
  //job:component variable/property
  //jobModel: Typescript type annotation. {}-Object Initialization

  //Depepndency Injection
  constructor(private jobService:JobService){}

  //called when the form is submitted
  createJob(){
    this.jobService.createJob(this.job).subscribe({
      next:(response)=>{
        console.log("Jon Created Successfully", response);
        alert("Job Created Successfully");
        this.resetForm();
      },
      error:(error)=>{
        console.log("Error in Creating Job", error);
        alert ("Failed to create Job");
      }
    });
  }

  //reset the form fields when the form is submitted
  resetForm() {

      this.job.companyName = '';
      this.job.jobTitle = '';
      this.job.description = '';
      this.job.additionalInfo = '';
      this.job.location = '';
      this.job.experience = null as any;
    }
}

/*
this.job means : instead of replacing the reference object angular modifies/updates the same object directly
 */
