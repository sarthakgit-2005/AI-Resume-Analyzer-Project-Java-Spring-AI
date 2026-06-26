import {Component, OnInit, ChangeDetectorRef} from '@angular/core';
import {JobModel} from '../../../core/models/job.model';
import {JobService} from '../../../core/services/job.service';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {Router, RouterLink} from '@angular/router';

@Component({
  selector: 'app-job-list',
  imports: [CommonModule,FormsModule, RouterLink],
  standalone:true,
  templateUrl: './job-list.html',
  styleUrls: ['./job-list.css'],
})
export class JobList implements OnInit {

  //get All Jobs
  jobs: JobModel[] = [];

  //filter
  companyName: string = '';
  location: string = '';
  jobTitle: string = '';

  expandedJobId: number | null = null;

  constructor(private jobService: JobService, private router: Router, private cdr: ChangeDetectorRef) {
  }

  ngOnInit(): void {
    console.log("Job List Component Loaded");
    this.getAllJobs();
  }

  getAllJobs() {
    this.jobService.getAllJobs().subscribe({
      next: (response: any) => {
        this.jobs = response;
        console.log(this.jobs);
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error Fetching Jobs', error);
      }
    });
  }

  deleteJob(id: number) {
    this.jobService.deleteJob(id).subscribe({
      next: (response) => {
        alert("Job Deleted Successfully");
        this.getAllJobs();
      },
      error: (error) => {
        console.error("Delete Failed", error);
      }
    });
  }

  //filter jobs
  filterByCompanyName() {
    if (!this.companyName.trim()) {
      return;
    }
    this.jobService.filterByCompanyName(this.companyName).subscribe({
      next: (response) => {
        this.jobs = response;
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  //filter by location
  filterByLocation() {
    if (!this.location.trim()) {
      return;
    }
    this.jobService.filterByLocation(this.location).subscribe({
      next: (response) => {
        this.jobs = response
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  //filter by jobTitle
  filterByJobTitle() {
    if (!this.jobTitle.trim()) {
      return;
    }
    this.jobService.filterByJobTitle(this.jobTitle).subscribe({
      next: (response) => {
        this.jobs = response
      },
      error: (error) => {
        console.error(error);
      }
    });
  }

  goToUpdate(jobId:number) {
      this.router.navigate(['/job-update', jobId]);
  }

  isExpanded:boolean=false;
  toggleFilters(){
    this.isExpanded=!this.isExpanded;
  }

  toggleJobDetails(jobId: number): void {

  if (this.expandedJobId === jobId) {
    this.expandedJobId = null;
  } else {
    this.expandedJobId = jobId;
  }

}
}

/*
subscribe
  this.jobService.getAllJobs()
    --returns an Observable, not actual data.
    --An Observable is like a data stream that doesn’t execute immediately.
    --So nothing happens until you subscribe to it.

  You use subscribe() when:
    --calling APIs (HTTP requests)
    --listening to real-time updates

 next:
      --callback that runs when the observable emits data sucessfully

error:
      --runs when the api calls fails


Why Angular uses Observables (not Promises)

    Observables are better because:
        ✔ Can handle multiple values over time
        ✔ Can be cancelled
        ✔ Powerful operators (map, filter, etc.)
        ✔ Used heavily in Angular ecosystem

 */
