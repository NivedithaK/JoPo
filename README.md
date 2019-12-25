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
	
<p align="center"><img src="https://lh3.googleusercontent.com/Mew4ikRFtOCZdgUSA66Z4Bw_MsxOWanuc9i1Dsj15TWBhDnPv08LipHCemBLXCLe2KMbG5ULIzERsF9mI5zP65g_u-LhGxartVx21q7MkHXd5UBpaRuhjzKZBe1YIg3xBC9jgQEVcDDxw_PSH3lwIB93AirmRyWNEo9nuPF-2zLkfk73IpJ0ZAEVcSnabWFLVofB9LNpLYXKRWu-ofzZuvebYl5amMLnmVd0CwQeyP8uAkCcCglNUmrXHGOEauoBwTejE7qWw1EoFGuus60scTlhLvlZCr69b5v9yuzQLv3wWP1UPu0Ydbp0ok9jgk6L2WqvYqqxt3dKHpQHNUbEfNEQsUGQU2WY8cxSYgTQogNuQ1ZzNwoJ32PllK8FOHi5p7FYwG6ZrZ__ucr2MW4R8KIhVBL1F-AqU-I33GeFjRLO3DSLIOCJOjZl0CYZjBAjjLvcz8zZMwhMzURJ6KdMyX2UQI1cRw2xgyhKTnTeRsWxYKPpyJkTgBRvz8UDD4fEfzX3zW3yI3QFNA63hgUo0tDhIQWHzsr7M1IJdIOQ_Zxd_nwAD_QVNznX0Q4h-40EQOeXlZ7MgKxOg646oJiebKfBzlGU67YSCZC8oPoP_gNbSZUvf2uaXTpjDu-CHOg72q7XzvZk316kRXgDQpVUO09ztyk7ORuHtg-e2n731KmvoUztTcH4XpQ=w1545-h1029-no" alt="Sign up"
	title="Sign up" width="500" height="300" /></p> 
	
<p align="center"><img src="https://lh3.googleusercontent.com/JoZAKYd9ixODRAGBFpQ5YAgZpHQpQO0y6dN9bTsPXZ0F6R-iQ05zpkqbdAAT7Ce-BPS2OL520PMs2SCXqYlZn5LYe4hHua_eVrUxyG5bGvVCnuT2gywVuoHXBlvzuPTfZbrBpRAU4G-XR0RNUj0Mi4BTCfM2iurP5GsBML3IPSvzUr_zw8RE1dg34zBzRAUX_WxVe6gZw07JdUF_UeT7gbUeMYXj6Ii26mZs1CHbg8u2JTN5XRFsVBbsl1sir9suAUK3abb44CgGkU6w7HcJp-Om0r4VnuYa6ViN0Vf2ROn3RdRAltoijtm7VWol2EdeowIeajP4T1GsI0p1qZT_r6gSbqsg6O8gZL3fiKbTETHdDLhDCbyUTFV04pVLhsjC80SDsIRU-5tE8Yro7GoWpFUgkYkmnjxD9lXWYpew9YzG3MHLdEgRZxMxayEEdaSGpyhC9_LoVhdCzfBaC-y_MK3O4n7x9rQYE-q-eq2g5-El8rdtEWS1yezw1tz5LOtNj4xEH8o6PyLAG0Cj3FRFi-7aI2zHFrv1zoTJ-H0jQzNm5JxSIcVorsuH6ONK451U_59Yn3aq2YvdLA94Mm8bl1oyelTAsMCMVtwNhiJneIl2jHmQGKmcRaD5mcuP3F8raNsTy-Ep7Pv0SmUbLPjGQApjfuqXHO36APWZAhNl1469lIlby6JVaio=w1434-h1062-no" alt="Choose interview type"
	title="Choose interview type" width="500" height="300" /></p> 
	
<p align="center"><img src="https://lh3.googleusercontent.com/LmAmNW0Z043CLnbphUeKfx9yof8REiHX1VJe60m_EeOlSdHhvTg9KHb3v5kq2hHs4LKUGe80PQICcZsm6AFHP8nNNBzy2uIkUuM-IcQzYjMNsywm6vqcA6zUi09CV39hYrXfyap4IWA0AjbcW8-T6bz1XelRcLpZPdS1os0BSdcqwwcp_xb2QJQA5iab7PRQSwA_0VLZldbYueMMOeHIr7MnekFrNpyd8h3tgfnf2opn7wHBD10IsXBvAF9QIzgcfgQ_8SkUmMsFm06dUG-N2zzaI51UTxN4M9nZDe629_FxvJZRCpDGzwNVQXvpJwR7YYxA0EtB8_vdySzeRNhWaD49CQdg6zXbSTTNjj9po7osCHXh3rVyhMlyPNteX2RFPr_MMXzCweKLYRFpqzvv6lw16NoDDIiJ-h6ZRdA1gAzA63bm1jEvDaXMP-NaFSZniqlC555ISKZX-tJs3Xa8ya39sQUdVILYdkz3HZzvkqPxGb3VQph3qshRW4nucyohjVdHOxw-i9FRgi9ra9MHmlH3SLinDBWUy6aSVs3eCJjm7yZubFs2j9lBlGKIfV2X8IfRvVsK-KIA1Drgd5WFZ1dfaXUQdjKQiuqWGRYa9iwMXeouWCOhxoJwg-Oi8A8W6u81HmWCuJmiexvLTAX9GrxtUwrOZJv0M9807v21V5hiReTwTT_wLBw=w1392-h1010-no" alt="Menu"
	title="Menu" width="500" height="300" /></p> 
  	
<p align="center"><img src="https://lh3.googleusercontent.com/e5ziaTU7Gr5wdxXPTP9F860IuAMzF-Mro9YvWwEED0psde_k8lK6Wgie4SiK5AfmEBMOERyVwKRpJUBqlZC9nPL5EZgBXPcOzSUnLFlPSlYKkUqUyQvpsYTkC4YDowrh2e6QSQxqDZFt5Svagqw3Xq_W_wIGqYilvJ37hLFaMvPmuMJnwaQ_-U_Fnsigzfv40g7Q_admHJJLZx5xQgB95DqPJxudDziXcIXlDxMHsZkYRRGLwF2mv96CnlRUblLswsE8Fci1eiwYp1iNM3-knNoFAZSTOfZltkU5F7YHutSKikmpRKt1sOEpBGnZuNdlj3UfRjbFmMlcnLxa5p8B9EUgP8st9YbYp8oy38UfnYTK5v0_MgBpfMFzkzJQwvWQgtyRNzmXCdXHbS4K4BvgBOplbxIIMsAQfXPLXFdJhbRUtmnol_t2LlfIhuhsbCjIIM2Cjy75W3Xz3WZnp7K-TmNXsHGE7il9Wlq7_JYxUYNqjKFWxZCTG2tzDe8TJNMMLMW6SPbPxjh4tI0oU7EnzqPNNgUspx3hHHfgEKZ8dMEB-lVCGneopOSmjpNy4K26Ckwf1hk25nZNFkATLRQ47J40JJ8wqP9YvvO5yvFxkn5dvHpCA3J6XgJreIAD-h1rEc2oACABgcDGPDSyes-LJ7C_m1B_eMh4Q3npVU3QsK4Uk3poj7skhnQ=w1450-h1032-no" alt="Add job"
	title="Add job" width="500" height="300" /></p> 
  
  <p align="center"><img src="https://lh3.googleusercontent.com/a33QY2q8nOzPnyUjCsK5SKTmaSO0LsqhxjZGNOSRrFcNoBIsWrE8eHt7uhQLp3l0NkeIzb3S7-vq46oz-O1J6orQrDYU07wShdT72q1aLVFoss0YgWaC3aBzvJt4iDWzJqSJZifalWBhfV9gsZhgOh6TNprc-m89PUzBoabE66hqlrUgWR-TQse0mZsHQs3t31Q9gcTp2APXKl4OhBJurfe74qm0Jy7oTSrizkCo73R7tk5g_zpJPUAD5VM_sTp-iwsJRbRM5dduTYYGns-_lSn_pQMY46APvXq4GYDYSzYjGLskjw6xBJz0FwDxgmZNCKp3W8TzDUUyBSC5tMI9rWK0ZwCfiQtt9SC0JT9AWaDH-bN1MlJnIK_u61krRJGlbBkTqlxd4apxWHBB0nueQ3bJ426YD37lHv0gDLBiHuXYAbmSJXeyydugGquohqQV6vKkl6UKKC-VMv4HVDqv_SSYemsJHoRK8nXH8mSqRp4nfVJCfLSZGAQ3xVCZuE0xnqmIa0FVivDb1X-uhWVRHErK3KpM8_-IX2rEHP8FrkJDxGpOtxrhH2KThd_I5vL0niXyodnH-UGJDK7NB0dxMKbQQE1Kro_jEWxtF9buoGU6zyJ6HwRxCf8XAc_Bse0jZU6F6NIRIPfyKMiuYvx6Iw43QUdwZV1W2kzgclcpYwONE5Qjh6JKG9E=w1424-h1070-no" alt="Interview type"
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
