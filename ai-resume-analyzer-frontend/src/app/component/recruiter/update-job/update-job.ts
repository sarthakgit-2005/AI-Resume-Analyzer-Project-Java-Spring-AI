import {Component, OnInit} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {JobService} from '../../../core/services/job.service';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-update-job',
  imports: [FormsModule, CommonModule],
  standalone:true,
  templateUrl: './update-job.html',
  styleUrl: './update-job.css',
})
export class UpdateJob implements OnInit  {
  jobId!:number;

  job={
    companyName:'',
    jobTitle:'',
    description:'',
    additionalInfo:'',
    experience:0,
    location:''
  }

  constructor(private router:Router, private route:ActivatedRoute, private jobService:JobService,private cd:ChangeDetectorRef){}

  ngOnInit(): void {

    this.route.paramMap.subscribe(params => {

      this.jobId = Number(params.get('id'));

      console.log("JOB ID =", this.jobId);

      this.loadJob();

    });

  }
    //loading the jobs
  loadJob(): void {

    this.jobService.getJobById(this.jobId).subscribe({

      next: (data) => {

        console.log("JOB DATA =", data);

        this.job = {
          companyName: data.companyName,
          jobTitle: data.jobTitle,
          description: data.description,
          additionalInfo: data.additionalInfo,
          experience: data.experience,
          location: data.location
        };

        this.cd.detectChanges();
      },

      error: (err) => {
        console.error(err);
      }

    });

  }

    //updatejob
    updateJob():void{
    this.jobService.updateJob(this.jobId,this.job).subscribe({
      next:(data)=>{alert("Job Updated Sucessfully");
      this.router.navigate(['/job-list'])},
      error:(err)=>{alert("Failed to update the job")}
    });
    }

    //goBack
  goBack():void{
    this.router.navigate(['/job-list']);
  }
}
