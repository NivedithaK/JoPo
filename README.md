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
	
<p align="center"><img src="https://lh3.googleusercontent.com/xY0r_SiNHIW8DC_M66cbr2C2EwRWjHlKzKYsZFf49WjTu3Eok5dYjtlDCeNO1Lm1SXDHjsvrqQvxZeZJlM6JQVQ6iTvlAcZQnJJ8kLEG5YpXK96mdWtGudlmMpKuISfofsMSFn5FzH8acT-haWTbQbr1R1EqIW3B0m34xGE3oixLMuq-qhUHAi-p9EikiD_ku-UD-ms7gF0aXbKhqqFdhaLKGnYcpajSBpKot-yNyV03DDDjTn5zwOSrYy7Za8QNVkXcWk7PrairolxWh8TzdNSa2Wzr_rBx2Y3Opw-Xf27yS7uzYzs_ZTgcCtQNjhpmA9fEzagih1a2z3fb1H_FtWRDlJJ4f2j-ekQhbtjiMjR7sZTl-6d9OkwQdPtWchWv70rfFTjJoj9Nl2JpeY40bw3MLNK3tF7zc7rbQw5sIw_82v2djBRBYIkYhYHsmg1TdW5bhcAoxktHXrGRQrWAwCoT3ClwzXPDWQBN5poDOtGSaGYS9mamGZTnM_9NqUj6D7NSPn1F5ek66al3kR9t42ykZ-PTPRhI0T9BesfL0YwH34nCsmeiQu8fZppb4VyOe4XCv_YTtG7SC-iTydlKTZfa1RPCYwTu3xmiJ4uRvF1WwL46gYQ5GxO5o2bXw3D3BSPzjsO-Vu1LOjmLD6_XtRkP_us83EW_AZKidyNCsrCRqwo_WUnoCHkjASkXrzoTtHBvuuD54bW_naLIC9MWQv5uuyc5dYtxWtKbeGuxMEZ_UUNX=w1460-h974-no" alt="Sign up"
	title="Sign up" width="500" height="300" /></p> 
	
<p align="center"><img src="https://lh3.googleusercontent.com/ZNei28mRtXSH5z8zIz4I3WhnwrXW3W0bAxZl8dIORqtRBGnCm-4VCFB_ySQpRHuAwgB7dFY7zm01dtvJTsN8Tq_gXALZgh8TG4WQ5BFV8C3kK_hFflfO_0FkKktFD1U4M4BwVGtcA-KQv9HrE5dtiJ9tu8ZpwvOb0xBjvh01H_Mf-hB7DuBZeQgHJ4bnrQdMQI1EKUfCYIyqcqD4_4FHhvazhFuB3ExbO0TJ4riq5RrbHGWMe9EJ9YxRJA9Dg1utCGu2fCIrkgHcnbHmtWs0HYowNYB3MKoSBPBmQeMdRM6wt2uLCRGr_3feuVgyaMm7zzh94lfbRdiDy8ti33ObKGgEWio9xZs7yUkrERhvZly71Bf9VulrvpnC5461phOZSBSdHW_9Bt6FpgYOcn0ssiHCnL6M5hTdh1GDAJ_jkQQkv3pGAmmqBlxjYgsPFfDecB2pwRzZpJ4d0uU2VVsk198GmpCus2jMuQ_umtvSV39ptqE6MTJF1kk0hMfxFkeUkty8Ix26N0ysFIu4QFWoWtyfAw00OikJMfooyQbvjNtHjcmIq0gSaNNUCf3ls9BaclGDKO_vthNw4IjQRFhuVOc8ksDcBUb920zybXc34fytWgWfi3AlNpSoN62S8l5Bzk8v7lx4rVbTUaUB32S0tSz4vu2WVZCfaIlPv23n4W-F_rGmOO4pHqy8VzMljxVjb7xYIXv9nLFmmQWh45DwvxBgw0tyAf9PU7-kTCHft5sbA0PZ=w1434-h1062-no" alt="Choose interview type"
	title="Choose interview type" width="500" height="300" /></p> 
	
<p align="center"><img src="https://lh3.googleusercontent.com/4-E730h8tfrSx0CVWtfhlUaYnA-8gZk5UqtF-3c7vnmOt1lT0ZMVljovkH9wN9J436PgZwrqYH0DAmZ6MxkSj_ppc-Ie0kHF2gbTIWe10d3CPPP_CTMCEPdzbvCwf8abzLrjK_HIPiVGtJ77lnInhsXkC0twyKwd2Pofg4IkR9jXmyO9jJlobfX8XfrRzsZAVD6Iav6g5-8gcYFVFbmCWsirzYYLODFu09X9-Ox8gU_tycsPK3BX82FkcHiKguKzan5AIfboaNQ1VWIqj3wwoIESIFvxs5BZdRUla5nWcnNxL_uUIGu6ykvphKsKeMvDykdWk0CHHGmM1yIjPJdf4VoK07q4qOVlBIXCunwB8uyAIrPpNEcnnFDu1dpnEqKqsEoQMkiTArJDcrwDbZtVtxgEhrdbHTbBs-SVm9r1x4DeI-pSQ2L1BO9whknrwRLzCCZLCxkODPeP9KtVptXGrwyZ2q9yY7C4kEelLpHwim_Bb6ULDTHMy5adek9nrSzkn1csiBSCfRPoc6C5anwxkXj6iaiLDe7TUEM9eHhsNIC7QL1JIAwlDQJtS3DKqretCU75c0lNwIRN5WuQYL8UcfTflIXIqMjCSUQ3yvRu3yXS1tHXvG1t51Bq1xA5gFL36iU2MUzvhAo7wkEPiBuTW_tzUuxJZstIfZrfW0iKtRrwKH_rGGiBkhQNPMurREi0NiGOCj_UKnvwNrKaklZxALX1XV-rqKEoqGvSlCaPKV4p8nUi=w1450-h1032-no" alt="Menu"
	title="Menu" width="500" height="300" /></p> 
  	
<p align="center"><img src="https://lh3.googleusercontent.com/wwai7mvsTO2KcRdC_zXzpnwjuJDEd4bizCh4sFytfpfUFZklrer7ThfRRZyjBcyQWJrDWbrZ6c2B_SVaIcwTDbi6hsDDX9d-G2PfQ-rkrRyI_LNdAg69g5syJ861BAxaLHG6JJaAav5DqqRJUVgqoBgBg54lHLMxIQiugke3u9DoJu6iZrGTO-vW_I3LD9xPeqj2VhIAUrmGnqHMWjZQeGcXcDBSKaI2t7JsKg1gzD8FZB7qnuFhCYTDZJ3X0DxzMsfPZA67TvCJag01XjQL3a-nLslmiiDmZsXDnQx2F74pJqkknuEfTUSkArHA1GGR6dvnRxuU4yQ36EYz18rZHtgwqpfysB5l9Ix0ITpjgrHuvgnHu2q3GRsLcMONfel8IMYRyoMlAVxDxo17StrrIkOQXqgosnMfPp366aEsu-wV3i5gAYJemrus0ctrG7hGJwSf3mOhO8QNgXNYZs6Jrr2Dfy0hQtabyeE04NtICMFlHxHNypvUEViH1IQkldpLrl1Kkue_MCFk2T0NaUOQgdVZ2d4hkygknOpbrsiytVtH2IJtqesdof7EJkvKgxM9NH5MNCMsfADHtLZBr9avqYOhckyQt80kL2eLAHQLs-y3j1DO18B1R2TYiL_czVG8u4CPvOV7SqmNrT_fty2-jV8_O6jU9mmNwPk1OmlDdRw2PJeimmmbtH3Pf2cQ4MjMjkQAXMz6E35pUOMhg0wLGf1dWDyWT4WkBp4JuPL6hymZhnJG=w1392-h1010-no" alt="Add job"
	title="Add job" width="500" height="300" /></p> 
  
  <p align="center"><img src="https://lh3.googleusercontent.com/oP9jcCgkaYrcSQRTaXNt3WFc2zQEBRrRsR3AelWUCFhcP4-vsG9YQkPyUN2m9FXncEhHAPQsXT8LA5RVNWbn-JD3XqzIiqMgs10y5ZR1O21tEiTKjOuT7vmIme7tre6fZhAKisq2kcTWKBxvZu4xbmSUiZx6qzNPCqb8AgHbNrQi144Xa6B8GaK64z_AjiHjd5zG0WqVBhGiOufSgumwdnyAlUuRpzszR4g1Rfr2fEOS_dgGq5QJFRVixe3X2Z9UCXHYpqc1j7CK4726_d5oL50XzNvmWmLG66b3TtBhFH6SctEmxS36BRlSiXdVOoo9HwL7SjbddhNpeS2xU2qRseKDCMpFkWCkDh3NuHOgmPSkOiXOHAfwXF0LYXne6a4Br5lLMAllsckgGitUqJdWlSJxDI9EhgmWSZ9V8Mv2GXXvSUBdzRRKz8ebQkAp7nkvAqaqqx4ug5mWd3-2SsP9HQSX4FjP8YwMy0k2kZSyif8xNlBm3F_RssvvPFyqxXXQ6OYcfo9t5cOdrSS5G3RDlS1ZVwUedfCuvWdAIXhLsydfjp7-nGWVxSYGazZ7FCCAduJ0k_eYf8vSn2aQ52Cv5_C7PLpnTlffrgg1dWXj4MQC_6-0wxcHxbRCJ4hoWwSdtDegp8tZmbdLtxsSSlvrxUcEbaPClR9GToJ4DYD7g1Xi3orF45zmuTXleKdZdhzVt0QtA0TccBq9OBb8cLPDoXfmDo1HFMrj1vuaIYBZhcZ2YJlM=w1424-h1070-no" alt="Interview type"
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
