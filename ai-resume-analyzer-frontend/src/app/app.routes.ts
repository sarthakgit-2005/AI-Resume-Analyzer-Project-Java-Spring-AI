import { Routes } from '@angular/router';           //Angular Routing Type

import { LandingPage } from './component/home/landing-page/landing-page';
import { JobCreate } from './component/recruiter/job-create/job-create';
import { JobList } from './component/recruiter/job-list/job-list';
import {RecruiterDashboard} from './component/recruiter/recruiter-dashboard/recruiter-dashboard';
import {ResumeListComponent} from './component/recruiter/resume-list/resume-list';
import {UpdateJob} from './component/recruiter/update-job/update-job';
import {AiResults} from './component/recruiter/ai-results/ai-results';
import { CandidateJobList } from './component/candidate/candidate-job-list/candidate-job-list';
import { ApplyJob } from './component/candidate/apply-job/apply-job';

export const routes: Routes = [           //Routes Array Stores All App URLs

  // ✅ LANDING PAGE (DEFAULT PAGE)
  //when user opens localhost:4200 then by default Landing Page Component will be loaded
  {
    path: '',
    component: LandingPage
  },

  // Recruiter Pages
  //url: http://localhost:4200/job-create
  {
    path: 'job-create',
    component: JobCreate
  },
  //url:http://localhost:4200/job-list
  {
    path: 'job-list',
    component: JobList
  },
  {
    path:'recruiter-dashboard',
    component:RecruiterDashboard
  },
  {
    path:'resume-list',
    component:ResumeListComponent
  },
  {
    path:'update-job/:id',
    component:UpdateJob
  },
  {
    path:'ai-results',
    component:AiResults
  },
  {
    path:'candidate-jobs',
    component: CandidateJobList
  },
  {
    path:'apply-job/:jobId',
    component:ApplyJob
  },
  // OPTIONAL fallback (wrong URLs)
  //If user opens wrong URL then angular redirects to home page
  {
    path: '**',
    redirectTo: ''
  }
];
