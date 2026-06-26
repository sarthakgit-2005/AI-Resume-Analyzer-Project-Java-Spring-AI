import { Injectable } from '@angular/core';
//Import Injectable Decorator
//Allows the class to be used as Dependency Injected Angular service
import { HttpClient } from '@angular/common/http';
//Used to make Http Request
import { Observable } from 'rxjs';
import {AIRequest} from '../models/ai-request.model';
import {AIResponse} from '../models/ai-response.model';
//Angular Http Methods return observable which handle async responses
//Make this class as angular service and only one instance is created , injected anywhere in the application

@Injectable({
  providedIn: 'root',
})

//Handles backend API Communication and seprates it from UI/Component layer
export class AiService {
  private baseURL='http://localhost:8095/api/ai';
  constructor(private  http:HttpClient){}
  analyze(request: AIRequest):Observable<AIResponse>{
    return this.http.post<AIResponse>(`${this.baseURL}/analyze`,request);
}
}
