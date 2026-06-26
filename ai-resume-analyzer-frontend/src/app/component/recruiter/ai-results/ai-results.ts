import { Component , OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { AiService } from '../../../core/services/aiService';
import { ResumeService } from '../../../core/services/resume.service';
import {CommonModule} from '@angular/common';
import { OnDestroy } from '@angular/core';
import { ChangeDetectorRef } from '@angular/core';
import { NgZone } from '@angular/core';

@Component({
  selector: 'app-ai-results',
  imports: [CommonModule],
  standalone:true,
  templateUrl: './ai-results.html',
  styleUrls: ['./ai-results.css'],
})
export class AiResults implements OnInit, OnDestroy{
  //all resumes from backend
  resumes:any[]=[];
  //show modal visibility
  showModal=false;
  //ai Analysis response data
  aiAnalysis:any;
  loading=false;

  constructor(private aiService:AiService, private resumeService: ResumeService, private cd: ChangeDetectorRef,
  private zone:NgZone)
  {
    console.log("AI Results Constructor Called");
  }

  ngOnInit():void {
    console.log("AI Resume Uploaded");
    this.loadAllResumes();
  }
  loadAllResumes(): void {

    // console.log("Calling Resume API");

    this.resumeService.getAllResumes().subscribe({

      next: (data) => {

        this.zone.run(() => {

          // console.log("RESUME DATA =", data);

          this.resumes = [...data];

          // console.log("AFTER ASSIGN =", this.resumes);

          this.cd.markForCheck();

        });

      },

      error: (err) => {

        console.error("API ERROR =", err);

      }

    });

  }
  openAnalysis(resumeId: number, jobId: number): void {

    this.zone.run(() => {

      this.showModal = true;

      this.loading = true;

      this.aiAnalysis = null;

      this.cd.markForCheck();

    });

    const requestBody = {
      resumeId,
      jobId
    };

    this.aiService.analyze(requestBody).subscribe({

      next: (data) => {

        this.zone.run(() => {

          // console.log("AI RESPONSE =", data);

          this.aiAnalysis = data;

          this.loading = false;

          this.showModal = true;

          this.cd.markForCheck();

        });

      },

      error: (err) => {

        this.zone.run(() => {

          console.error(err);

          this.loading = false;

          this.cd.markForCheck();

        });

      }

    });

  }

  closeModal():void{
      this.showModal=false;
}

  ngOnDestroy(): void {
    console.log("AI Results Destroyed");
  }

}

