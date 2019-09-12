package src.managers;

import src.filters.JobPostingFilter;
import src.main.JobPortal;
import src.main.JobPosting;
import users.Applicant;
import java.io.Serializable;
import java.util.ArrayList;

public class ApplicantManager implements Serializable {

    JobPortal jp;

    public ApplicantManager (JobPortal jp) {
        this.jp = jp;
    }

    public void menu (Applicant user) {
        int choice = user.menu();
        while (choice != 7) {
            switch (choice) {
                case 1: //View job postings
                    JobPostingFilter jobFilter = new JobPostingFilter();
                    ArrayList filteredJobs = jp.filtermanager.filterJobs(jobFilter);
                    jp.filtermanager.printJobs(filteredJobs);
                    break;
                case 2: //Apply to job posting by ID
                    int selection = user.apply();
                    if (selection != -1) {
                        JobPosting interestedJob = jp.getJobByID(selection);
                        if (interestedJob != null) {
                            if (!user.jobAlreadyExists(interestedJob)) {
                                user.beginApplication(interestedJob);
                                interestedJob.getCompany().receiveJobApplication(user, interestedJob);
                            }
                            else {System.out.println("You have already applied for this job.");}
                        }
                        else {
                            System.out.println("[Error] Job ID not found.");
                        }
                    }
                    break;
                case 3: //View documents
                    int num = user.docsMenu();
                    while (num != 6) {
                        user.displayDox(num);
                        num = user.docsMenu();
                    }
                    break;
                case 4: //View status of applications
                    user.viewStatus();
                    break;
                case 5: //View history
                    user.viewUserHistory();
                    break;
                case 6: //Rate interviewer
                    user.rateInterviewer();
                    break;
            }
            choice = user.menu();
        }
    }

}

