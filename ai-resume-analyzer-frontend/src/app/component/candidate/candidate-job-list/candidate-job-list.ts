import { Component, OnInit } from '@angular/core';
import { JobService } from '../../../core/services/job.service';
import { JobModel } from '../../../core/models/job.model';
import { Router} from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

@Component({
  selector: 'app-candidate-job-list',
  imports: [CommonModule, FormsModule],
  standalone: true,
  templateUrl: './candidate-job-list.html',
  styleUrl: './candidate-job-list.css',
})
export class CandidateJobList implements OnInit{
  
  //component state variables
  jobs:JobModel[]=[];

//used for filtering the jobs as they are created and stored separately. 
// they store the text data (which user enters for filtering the jobs)
  companyName: string = '';
  location: string = '';
  jobTitle: string = '';

  //used to expand filters/ job description
  isExpanded: boolean = false;
  expandedJobId: number | null = null;

  constructor(private jobService:JobService, private router:Router, private cdr:ChangeDetectorRef){
  }
  ngOnInit(): void {
    this.getAllJobs();
  }

    getAllJobs():void{
      this.jobService.getAllJobs().subscribe({
        next:(response)=>{this.jobs=response;this.cdr.detectChanges();},
        error:(error)=>{console.log(error)}
      });
    }
    
    filterByCompanyName():void{
      if(!this.companyName.trim()){
        return;
      }
      this.jobService.filterByCompanyName(this.companyName).subscribe({
        next:(response)=>{this.jobs=response},
        error:(error)=>{console.log(error)}
      });
    }

    
    filterByLocation():void{
      if(!this.location.trim()){
        return;
      }
      this.jobService.filterByLocation(this.location).subscribe({
        next:(response)=>{this.jobs=response},
        error:(error)=>{console.log(error)}
      });
    }

    
    filterByJobTitle():void{
      if(!this.jobTitle.trim()){
        return;
      }
      this.jobService.filterByJobTitle(this.jobTitle).subscribe({
        next:(response)=>{this.jobs=response},
        error:(error)=>{console.log(error)}
      });
    }

    toggleFilters():void{
      this.isExpanded=!this.isExpanded;
    }

    toggleJobDetails(jobId:number):void{
      if(this.expandedJobId==jobId)
      {
        this.expandedJobId=null;
      }
      else{
        this.expandedJobId=jobId;
      }
    }

    applyJob(jobId:number):void{
      this.router.navigate(['/apply-job',jobId]);
    }
}
