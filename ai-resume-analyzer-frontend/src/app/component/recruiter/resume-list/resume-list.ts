import {Component, OnInit} from '@angular/core';
import { ChangeDetectorRef } from '@angular/core';
import { ResumeService } from '../../../core/services/resume.service';
import { ResumeList } from '../../../core/models/resume-list.model';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-resume-list',
  standalone:true,
  imports: [CommonModule, FormsModule],
  templateUrl: './resume-list.html',
  styleUrls: ['./resume-list.css']
})
export class ResumeListComponent implements OnInit {

  resumes: ResumeList[] = [];

  // recruiter enters this
  jobId!: number;

  constructor(private resumeService: ResumeService, private cdr : ChangeDetectorRef) {}
  ngOnInit(): void {

    setTimeout(() => {

      this.loadAllResumes();

    }, 100);

  }

  // Filter resumes by Job ID
  filterByJobId(): void {
    if (!this.jobId) {
      alert('Please Enter Job ID');
      return;
    }
    this.resumeService.getResumesByJobId(this.jobId).subscribe({
        next: (data) => {
          this.resumes = data;
          },
        error: (err) => {
          console.error(err);
          alert('No Resumes Found');
        }
      });
  }

  // View Resume
  viewResume(resumeId: number): void {
    window.open(`http://localhost:8095/api/resumes/view/${resumeId}`, '_blank');
  }

  // Download Resume
  downloadResume(resumeId: number): void {
    this.resumeService.downloadResume(resumeId).subscribe({
        next: (blob) => {
          const url = window.URL.createObjectURL(blob);
          const a = document.createElement('a');
          a.href = url;
          a.download = 'resume.pdf';
          a.click();
          window.URL.revokeObjectURL(url);
        },
        error: (err) => {
          console.error(err);
        }
      });
  }
  loadAllResumes():void{
    this.resumeService.getAllResumes().subscribe({
      next:(data)=>{this.resumes=data, this.cdr.detectChanges()},
    error:(err)=>console.error(err)});
  }
}

/*
next:callback function inside the Observable subscription
It runs when the http request is successful and data is received from backend

why to use next:
  because HTTP calls are asynchronous so angular needs a way to say that the success result is here and do something with it
  asynchronous: The code does not wait for a task to finish before moving to the next line

  When something is asynchronous:
    It starts a task
    Moves on immediately
    Comes back later when result is ready

    Why Angular uses asynchronous HTTP calls?
    Because:
            Backend may take time (database, network, server)
            If Angular waited, UI would freeze
            App would become slow and unresponsive

          So Angular uses async → smooth user experience
 */
