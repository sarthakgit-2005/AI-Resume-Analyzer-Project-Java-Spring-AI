import { Component } from '@angular/core';        //used to create angular component
import { Router } from '@angular/router';         //used for page navigation between routes

//the below decorator provides metadata about the component

@Component({
  selector: 'app-landing-page',       //defines html tag for the component
  standalone:true,                    //It does not need to be declared inside an Angular module
  imports: [],                      //used to import other standalone components
  templateUrl: './landing-page.html',
  styleUrl: './landing-page.css',
})
export class LandingPage {
  constructor(private router:Router){}
 
  goToRecruiterDashboard(){
  this.router.navigate(['/recruiter-dashboard']);
}

goToUserDashboard(){
  this.router.navigate(['/candidate-jobs']);
}
}
