package src.managers;

import utilities.IBIO;
import src.main.JobPortal;
import users.Interviewer;
import java.io.Serializable;

public class InterviewerManager implements Serializable {

    private JobPortal jp;

    public InterviewerManager (JobPortal jp) {
        this.jp = jp;
    }

    public void menu(Interviewer user) {
        checkNewUser(user);
        int choice = user.menu();
        while (choice != 5) {
            switch (choice) {
                case 1: //Sign up for interviews
                    InterviewManager interviewMan = user.getIntManager();
                    int num = interviewMan.displayInvitedInterviews();
                    user.signUpForInterview(num);
                    break;
                case 2: //Recommend/Reject applicants
                    manageApplicants(user);
                    break;
                case 3: //View cover letter and CV of an applicant
                    user.viewDocuments();
                    break;
                case 4: //View profile
                    user.viewProfile();
                    break;
            }
            choice = user.menu();
        }
    }

    private void checkNewUser(Interviewer user) {
        if (user.getName() == null) {
            System.out.println("______________________");
            System.out.println("| Set-Up New Account |");
            System.out.println("|____________________|");
            user.setName(IBIO.inputString("Please enter your name: "));
            changePassword(user);
        }
    }

    private void changePassword(Interviewer user) {
        String oldpass = IBIO.inputString("Please enter your old password: ");
        String newpass = IBIO.inputString("Please enter your new password: ");
        user.changePassword(oldpass, newpass);
    }

    public void manageApplicants(Interviewer user) {
        boolean recommendApp;
        String decision = IBIO.inputString("| Would you like to recommend applicant(s)? (y) recommend, (n) reject, or (q) quit: ");
        if (!decision.equals("q")) {
            recommendApp = (decision.equals("y"));
            user.recommendApplicant(recommendApp);
        }
    }
}
