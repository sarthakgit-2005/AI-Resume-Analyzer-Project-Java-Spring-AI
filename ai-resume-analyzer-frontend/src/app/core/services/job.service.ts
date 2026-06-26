import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient} from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class JobService {
  private BaseURL='http://localhost:8095/api/jobdesc';
  constructor(private http:HttpClient){}

  //create Job
  createJob(jobData:any):Observable<any>{
    return this.http.post(`${this.BaseURL}/create`,jobData);
  }

  //update Job
  updateJob(id:number, jobData:any):Observable<any>{
    return this.http.put(`${this.BaseURL}/update/${id}`, jobData);
  }

  //getJobById
  getJobById(id:number):Observable<any>{
    return this.http.get(`${this.BaseURL}/${id}`);
  }

  //delete job
  deleteJob(id:number):Observable<any>{
    return this.http.delete(`${this.BaseURL}/delete/${id}`);
  }

    //get All Jobs
  getAllJobs():Observable<any>{
    return this.http.get(`${this.BaseURL}/all`);
  }

  //filter by company
  filterByCompanyName(companyName:string):Observable<any>{
    return this.http.get(`${this.BaseURL}/company/${companyName}`);
  }

  //filter by jobTitle
  filterByJobTitle(jobTitle:string):Observable<any>{
    return this.http.get(`${this.BaseURL}/jobTitle/${jobTitle}`);
  }

  //filter by location
  filterByLocation(location:string):Observable<any>{
    return this.http.get(`${this.BaseURL}/location/${location}`);
  }

}
