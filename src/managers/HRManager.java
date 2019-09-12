package src.managers;

import src.filters.DocumentFilter;
import utilities.IBIO;
import src.main.JobPortal;
import users.HRCoord;
import users.Interviewer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class HRManager implements Serializable {

    private JobPortal jp;

    public HRManager(JobPortal jp) {
        this.jp = jp;
    }

    public void menu(HRCoord user) {
        int choice = user.menu();
        while (choice != 9) {
            switch (choice) {
                case 1: //Add new job or Delete existing jobs
                    manageJobs(user, this.jp.getIdManager());
                    break;
                case 2: //View all jobs at own company
                    user.getCompany().printJobs();
                    break;
                case 3: //View applicants by job posting
                    DocumentFilter doxFilter = new DocumentFilter();
                    ArrayList filteredDox = user.filterApplicantDox(doxFilter);
                    for (Object d : filteredDox) {
                        FileManager fm = new FileManager();
                        fm.viewDocument((String) d);
                    }
                    break;
                case 4: //Arrange interviews
                    user.arrangeInterviews();
                    break;
                case 5: //Add new interview type for own company
                    user.addInterviewType();
                    break;
                case 6: //Rate interviewer
                    user.rateInterviewer();
                    break;
                case 7: //Hire applicant
                    int num = user.hireForJob();
                    user.hireAppForJob(num);
                    break;
                case 8: //Create interviewer account
                    Interviewer newint = createInterviewer(user, this.jp.getIdManager());
                    printInterviewer(newint);

            }
            choice = user.menu();
        }
    }

    public Interviewer createInterviewer(HRCoord user, IDManager idMan) {
        Random rand = new Random();
        String password = "p";
        for (int i = 0; i < 5; i++) {
            char temp = (char) (rand.nextInt(26) + 'a');
            password = password.concat(String.valueOf(temp));
        }
        int newID = idMan.newUserID();
        Interviewer newInterviewer = new Interviewer(user.getCompany(), newID, null, password);
        user.getCompany().addInterviewers(newInterviewer);
        jp.addUserToList(newInterviewer);
        return newInterviewer;
    }

    public void printInterviewer(Interviewer interviewer) {
        System.out.println("________________________");
        System.out.println("| New Account Created! |");
        System.out.println("|______________________|\n");
        System.out.println("User ID: " + interviewer.getUserID());
        System.out.println("Password: " + interviewer.getPassword());
        System.out.println("Role: Interviewer");
        System.out.println("Company: " + interviewer.getCompanyName());
    }

    public void manageJobs(HRCoord user, IDManager IDMan) {
        JobPostingManager jpManager = user.getJobPostingManager();
        boolean addJob;
        String decision = IBIO.inputString("| Would you like to (a) add job(s), (d) delete job(s), or (q) quit?: ");
        if (!decision.equals("q")) {
            addJob = (decision.equals("a"));
            if (addJob) {
                int newPostID = IDMan.newPostID();
                jpManager.addJobPosting(newPostID);
            }
            else {
                jpManager.deleteJobPosting();
            }
        }
    }
}
