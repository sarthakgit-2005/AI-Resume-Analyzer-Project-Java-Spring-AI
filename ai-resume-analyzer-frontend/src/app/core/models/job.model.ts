export interface JobModel {
  id ?:number;
  companyName: string;
  jobTitle : string;
  description : string;
  additionalInfo : string;
  location : string;
  experience : number;
}

/*
Models are the typescript interfaces the=at define the structure of data coming from backend i.e from springboot
 */
