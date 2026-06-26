\# 🚀 AI Resume Analyzer

\### AI Powered Resume Screening System using Spring AI, Spring Boot Microservices, Ollama \& Angular



\## 📌 Project Overview



AI Resume Analyzer is a full-stack, AI-powered Resume Screening System designed to automate the recruitment workflow.



Instead of manually reviewing hundreds of resumes, recruiters can create job openings, while candidates upload their resumes for specific jobs. The system automatically extracts structured information from resumes using AI, compares it with the Job Description, calculates an ATS (Applicant Tracking System) score, identifies matched and missing skills, and generates AI-powered recommendations.



The project follows a Microservices Architecture, where every business functionality is implemented as an independent Spring Boot service communicating through REST APIs behind an API Gateway.



The application also includes a modern Angular frontend that provides separate interfaces for Recruiters and Candidates.



\---



\# ✨ Key Features



\## 👨‍💼 Recruiter Portal



\- Create Job Descriptions

\- View All Jobs

\- Update Existing Jobs

\- Delete Jobs

\- Search Jobs by:

&#x20;   - Company

&#x20;   - Job Title

&#x20;   - Location

\- View Resume List (For Specific Job and also search Resume By Id)

\- Download Candidate Resume

\- View AI Analysis 

\- ATS Score

\- Missing Skills

\- Matched Skills

\- AI Recommendation



\---



\## 👨‍🎓 Candidate Portal



\- Browse Available Jobs

\- Search Jobs

\- View Complete Job Details

\- Apply for Specific Job

\- Upload Resume (PDF)

\- One Candidate → One Application Per Job

\- Automatic Resume Processing



\---



\# 🤖 AI Powered Resume Processing



After a candidate submits a resume, the system performs the following AI pipeline automatically:



\### Step 1

Resume is uploaded into Resume Service.



↓



\### Step 2

Resume Parser Service extracts text from the PDF.



↓



\### Step 3

Spring AI + Ollama parses the resume into structured JSON.



Extracted Information:



\- Candidate Name

\- Skills

\- Education

\- Experience

\- Projects

\- Certifications



↓



\### Step 4



AI Service fetches:



\- Parsed Resume

\- Job Description



↓



\### Step 5



Spring AI compares both documents and generates:



\- ATS Score

\- Matched Skills

\- Missing Skills

\- Candidate Recommendation



↓



\### Step 6



The processed results are stored in PostgreSQL and become available to Recruiters.



\---



\# 🏗 Project Architecture



The project follows a Microservices Architecture.



```

Angular Frontend

&#x20;       │

&#x20;       ▼

API Gateway

&#x20;       │

──────────────────────────────────────

│

├── Job Description Service

├── Resume Service

├── Resume Parser Service

└── AI Service

&#x20;       │

&#x20;       ▼

PostgreSQL Database

```



Each microservice is independently deployable and communicates using REST APIs.



\---



\# 🔄 Complete Workflow



\## Recruiter Flow



```

Recruiter Dashboard



&#x20;       │



Create Job



&#x20;       │



Job Stored in Database



&#x20;       │



Candidate Applies



&#x20;       │



Recruiter Opens Resume List



&#x20;       │



Recruiter Views AI Results



&#x20;       │



Recruiter Downloads Resume

```



\---



\## Candidate Flow



```

Landing Page (End User Card)



&#x20;     │



Browse Jobs



&#x20;     │



Filter Jobs



&#x20;     │



Apply for Job



&#x20;     │



Fill Details



&#x20;     │



Upload Resume



&#x20;     │



Submit Application



&#x20;     │



Resume Stored



&#x20;     │



AI Processing Starts

```



\---



\# 🧠 AI Processing Workflow



```

Resume Upload



&#x20;       │



Resume Service



&#x20;       │



Resume Parser Service



&#x20;       │



Extract Resume Text



&#x20;       │



Spring AI + Ollama



&#x20;       │



Structured Resume JSON



&#x20;       │



AI Service



&#x20;       │



Compare with Job Description



&#x20;       │



Generate ATS Score



&#x20;       │



Generate Recommendations



&#x20;       │



Save Results

```



\---



\# 📂 Microservices



\## 1️⃣ Job Description Service



Responsible for Job Management.



\### Responsibilities



\- Create Job

\- Update Job

\- Delete Job

\- Fetch Jobs (View All Jobs)

\- Search Jobs (Filter By Company Name/Location/JobTitle)



\---



\## 2️⃣ Resume Service



Responsible for Candidate Resume Management.



\### Responsibilities



\- Upload Resume

\- Download Resume

\- View Resume

\-getResumeById

\-getResumeByJobId

\-deleteResume

\-getAllResumes



\---



\## 3️⃣ Resume Parser Service



Responsible for Resume Parsing.



Uses



\- Spring AI

\- Ollama



Extracts



\- Skills

\- Education

\- Experience

\- Projects



Returns Structured JSON Resume Data.



\---



\## 4️⃣ AI Service



Responsible for AI Analysis.



Performs



\- Resume Parsing Result Retrieval

\- Job Description Retrieval

\- Skill Matching

\- ATS Score Calculation

\- Missing Skill Detection

\- AI Recommendation Generation



\---



\# 🗄 Database



The application uses PostgreSQL.



\## Job Table



Stores

\- id

\- Company Name

\- Description

\- Additional Information

\- Location

\- Experience

\- Job Title



\---



\## Resume Table



Stores



\- id

\- Candidate Name

\- Candidate Email

\- File Name

\- File Path

\- File Type

\- Job Id

\- Phone

\- uploaded\_at

\- Skills

\- Education

\- Experience

\- Projects

\- Certifications

\---



\# 🛠 Technology Stack



\## Frontend



\- Angular

\- TypeScript

\- HTML5

\- CSS3

\- Angular Router

\- FormsModule

\- HttpClient



\---



\## Backend



\- Java 21

\- Spring Boot

\- Spring AI

\- Spring Data JPA

\- Lombok

\- REST APIs



\---



\## AI



\- Ollama

\- Llama 3

\- Spring AI



\---



\## Database



\- PostgreSQL



\---



\## Build Tools



\- Maven

\- npm

\-ng



\---



\## Version Control



\- Git

\- GitHub



\---



\## 📷 Application Screenshots \& Architecture



\### System Architecture

!\[Architecture Diagram](Architecture\_Diagram\_For\_AI\_project.png)



\### Landing Page

!\[Landing Page](Landing\_Page.png)



\### Candidate Job Listing

!\[Candidate Job Listing](Candidate\_Job\_Listing\_Page.png)



\### Recruiter Dashboard

!\[Recruiter Dashboard](Recruiter\_Dashboard.png)



\### Resume List

!\[Resume List Page](Resume\_List\_Page.png)



\###AI Results Page

!\[AI Results Page](AI\_Results\_Page.png)



\###AI Analysis Page

!\[AI Analysis Page](AI\_Analysis\_Page.png)



\### Recruiter Management (CRUD)

!\[Recruiter CRUD Page](Recruiter\_CRUD\_Page.png)



\# 🚀 Future Enhancements



\- JWT Authentication \& Role-Based Authorization

\- Email Notification after Application Submission

\- Candidate Dashboard

\- Asynchronous AI Processing using `@Async` or Message Queue

\- Cloud Deployment (Render/AWS)



\---



\# 📖 Learning Outcomes



This project demonstrates practical implementation of:



\- Microservices Architecture

\- REST API Communication

\- Spring AI Integration

\- Ollama Integration

\- AI Powered Resume Parsing

\- ATS Score Generation

\- Angular Frontend Development

\- PostgreSQL Database Design

\- File Upload Handling

\- PDF Processing

\- Clean Layered Architecture

\- DTO Pattern

\- Repository Pattern

\- Service Layer Design

\- API Gateway Routing



\---



\# 📄 Project Summary



AI Resume Analyzer is a production-style recruitment platform that combines modern Java backend development with Generative AI to automate resume screening. Recruiters can efficiently manage job postings and evaluate candidates using AI-generated insights, while candidates enjoy a streamlined application process. The system showcases real-world concepts such as Spring Boot Microservices, API Gateway, Spring AI, Ollama integration, RESTful communication, PostgreSQL persistence, and Angular frontend development, making it a comprehensive full-stack AI project suitable for enterprise-level applications.

