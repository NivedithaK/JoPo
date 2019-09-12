# JoPo (Job Portal)

A job market program coded with Java that facilitates exchanges between individual 
job applicants, company HR coordinators, and company interviewers. 

## Navigation
<a name="top"></a> 
1. [Installation](#install) 
2. [Screenshots] (#screenshots)
3. [Program Features](#feature)
4. [Repository Structure](#structure)
5. [Testing Example] (#example)
6. [Credits](#credits)

## <a name="install"></a>Installation

For either of the following options:
- File > Project Structure
  - Set “Project SDK” to 12.
  - Set “Project compiler output” to any output folder.

To run the program in console: 
1) Run the Main class (path: ./phase2/src/main/Main).
2) No additional packages need to be installed.

To view the unimplemented GUI:
1) Install javafx-sdk-12.
2) File > Project Structure
   a) SDKs (left sidebar) > Add the folder javafx-sdk-12/lib to both Classpath and 
      Sourcepath.
   b) Libraries (left sidebar) > Add the folder javafx-sdk-12/lib.
3) Edit Configurations
   a) Main class: ui.MainUI
   b) VM options: --module-path (insert path here)/javafx-sdk-12.0.2/lib --add-
      modules=javafx.controls,javafx.fxml

[Back to top](#top)

## <a name="screenshots"></a>Screenshots
	
<p align="center"><img src="https://i.imgur.com/vy8IqA7.png" alt="Sign up"
	title="Sign up" width="500" height="300" /></p> 
	
<p align="center"><img src="https://i.imgur.com/Uuhs51H.png" alt="Choose interview type"
	title="Choose interview type" width="500" height="300" /></p> 
	
<p align="center"><img src="https://i.imgur.com/OXbXUeF.png" alt="Menu"
	title="Menu" width="500" height="300" /></p> 
  	
<p align="center"><img src="https://i.imgur.com/v3RPFIe.png" alt="Add job"
	title="Add job" width="500" height="300" /></p> 
  
  <p align="center"><img src="https://i.imgur.com/uzRuJgi.png" alt="Interview type"
	title="Interview type" width="500" height="300" /></p> 
	
[Back to top](#top)

## <a name="feature"></a>Program Features 

- HR coordinators can add their company to the job portal and post jobs.
- Applicants can apply to jobs and upload the required documents by submitting a 
  local path to the file. Required documents are copied into a folder (with generated 
  file name “userid_filetype_jobpostingid.txt”) within the project, and can be 
  pushed/pulled and accessed by another computer.
- Interviewers can recommend or reject applicants that they have been assigned to 
  interview.
- There are four options to assign interviewers to interviews, including:
  a) Automatic selection from roster
  b) Prioritization based on interviewer rating or experience
  c) Interviewer self-signup
  d) HR prompted to select interviewers
- Applicants can filter job postings by tags, titles, days left to apply, requirements, 
  companies, and more.
- HR Coordinators can view applicant documents (text documents will be printed in the 
  console).
- HR Coordinators can generate Interviewer accounts with a random password, and 
  Interviewers will be prompted to change their name and password when setting up a new 
  account. This prevents Applicants from creating their own Interviewer accounts and hiring 
  themselves on behalf of the company.
- Both Applicants and HR Coordinators can give Interviewers a rating from 1-10.
- Interview types and required documents are dynamic and customizable.
- The program is serialized upon logging out so all data is saved.
- Applicants are notified when they are hired for a job. 
- Hiring happens automatically when the number of applicants left in the applicant pool 
  after a round of interviews equals the number of openings for a job posting.


[Back to top](#top)

## <a name="structure"></a>Repository Structure

User package: Contains all users that inherit the abstract class User. Most methods in 
these classes interact with the user.
- Applicant
- Interviewer
- HR Coordinator

Manager package: Handles the backend commands resulting from user input. 
- LoginManager
- HRManager
- JobPostingManager

Main package: Contains the key objects required in the interview process.
- JobPosting
- Company
- JobApplication

Utilities package: Contains libraries for user text input.
- IBIO

GUI package: Contains Controller classes and FXML files handling all GUI code.
- SignupController
- DataModel
- MainUI

[Back to top](#top)

## <a name="example"></a>Testing example

Upon running src.main.Main, make the following inputs for a full run-through of 
the job hiring process, from HRCoord account creation to notifying an Applicant 
that they have been hired. 

1) Creating HRCoord account:
\>y
\>Ben
\>n
\>BK
\>123

2) Creating new JobPosting:
\>Online
\>q
\>1
\>a
\>Chef
\>CV
\>q
\>10
\>food
\>1
\>1
\>2
\>1
\>1
\>-1

3) Generate Interviewer account:
\>8 (please record the UserID and Password for later)

4) Logout:
\>9
\>no

5) Creating Applicant account:
\>y
\>Ben
\>y
\>123
\>CV.txt (*preferably upload real paths here)
\>CL.txt*

6) Applying to a JobPosting:
\>1
\>a
\>2
\>100
\>1
\>CV.txt
\>4

7) Logout:
\>7
\>no

8) Log back into HRCoord and start Interviews:
\>n
\>100000
\>123
\>4
\>100
\>9
\>no

9) First time logging in as an Interviewer:
\>n
\>(input the UserID and Password from "Generate Interviewer account")
\>Ben
\>(input Password again)
\>123

10) Interview and recommend/hire an Applicant:
\>1
\>1
\>-1
\>2
\>y
\>100002
\>100
\>-1

11) Logout:
\>5
\>no

12) Log back into Applicant and see application status:
\>n
\>100002
\>123
\>4

Congrats! The applicant has been hired for that job. To save your progress at any
time, enter the option for "log out" and then type "true" to quit the program and 
serialize your data.

## <a name="credits"></a>Credits

Group 0136 consists of Niveditha Kani, Vanessa Chu, Jingneng (Layana) He, 
Jo-Yen (Julianne) Lin, Yue Li, and Yujia (Cathy) Cheng. We would like to thank 
the instructors David Jorjani and Lindsey Shorser, our lab TA Morteza, along 
with all the other TAs involved with teaching and guiding us through CSC207 
this semester.

This project was completed for CSC207 at the University of Toronto on August
9th, 2019. 

[Back to top](#top)
