import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ResumeList } from '../models/resume-list.model';

@Injectable({
  providedIn: 'root',
})
export class ResumeService {

  private BaseURL = 'http://localhost:8095/api/resumes';

  constructor(private http: HttpClient) {}

  // Upload Resume
  uploadResume(formData: FormData): Observable<any> {
    return this.http.post(`${this.BaseURL}/upload`, formData);
  }

  // Get Resume By Id
  getResumeById(id: number): Observable<any> {
    return this.http.get(`${this.BaseURL}/${id}`);
  }
  // Resume List API
  getResumesByJobId(jobId: number): Observable<ResumeList[]> {
    return this.http.get<ResumeList[]>(`${this.BaseURL}/job/${jobId}`);
  }
  // Delete Resume (Check whether it is working from frontend)
  deleteResumeById(id: number): Observable<any> {
    return this.http.delete(`${this.BaseURL}/${id}`);
  }
  // Download Resume
  downloadResume(id: number): Observable<Blob> {
    return this.http.get(`${this.BaseURL}/download/${id}`,
      {
        responseType: 'blob'
      }
    );
  }
  //getAllResumes
  getAllResumes():Observable<any> {
    return this.http.get(`${this.BaseURL}`);
  }
}
/*
Springboot backend returns resource which represent PDF file, DOC, Binary File Data
The response is binary File Data

Blob means:
Binary Large Object
Used for:
PDF files
images
videos
documents
zip files

  //getPendingAI Resumes
  getPendingAIResumes(): Observable<any> {
    return this.http.get(`${this.BaseURL}/pending-ai`);
  }
  //get Ai Processed Resumes
  getAIProcessedResumes(): Observable<any> {
    return this.http.get(`${this.BaseURL}/ai-processed`);
  }

*/
