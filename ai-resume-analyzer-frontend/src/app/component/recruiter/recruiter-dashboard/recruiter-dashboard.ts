import { Component } from '@angular/core';
import {Router, RouterLink} from '@angular/router';       //used for angular routing

@Component({
  selector: 'app-recruiter-dashboard',
  standalone:true,        //means component works independently without module file
  imports: [RouterLink],
  templateUrl: './recruiter-dashboard.html',
  styleUrls: ['./recruiter-dashboard.css']
})
export class RecruiterDashboard {
  constructor(private router:Router) {      //injects angular router
  }
  //Navigate to crate job
  goToCreateJob(){
    this.router.navigate(['/job-create']);
  }

  goToJobList(){
    this.router.navigate(['/job-list']);
  }

  goToResumeList(){
    this.router.navigate(['/resume-list']);
  }

  goToAiResults(){
    this.router.navigate(['/ai-results']);
  }

  goToResumeDetails(){
    this.router.navigate(['/resume-details']);
  }
}
