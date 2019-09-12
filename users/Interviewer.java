package users;

import src.main.Interview;
import src.main.JobPosting;
import src.main.Company;
import src.managers.FileManager;
import src.managers.InterviewManager;
import utilities.IBIO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Interviewer extends User implements Serializable {

    private Company company;
    private Calendar dateCreated;
    private InterviewManager interviewMan;
    private ArrayList<Integer> ratingsList;     // [ratingGivenByHR, numHRRaters, ratingGivenByInterviewees,
    // numIntervieweeRaters, experienceByDays, experienceBy#ofPastInts]

    public Interviewer(Company company, int id, String name, String password) {
        super(id, password, name);
        this.company = company;
        this.ratingsList = new ArrayList<>();
        this.dateCreated = Calendar.getInstance();
        this.interviewMan = new InterviewManager(this);
        ratingsList.add(0);
        ratingsList.add(0);
        ratingsList.add(0);
        ratingsList.add(0);
    }

    public int menu() {
        System.out.println("____________________");
        System.out.println("| Interviewer Menu  |");
        System.out.println("|___________________|\n");
        System.out.println("| (1) Sign up for interviews");
        System.out.println("| (2) Recommend/Reject applicants");
        System.out.println("| (3) View cover letter and CV of an applicant");
        System.out.println("| (4) View profile");
        System.out.println("| (5) Log out");
        return (IBIO.inputInt("| Enter a number: "));
    }

    public void addRating(String type, int rating) {
        switch (type) {
            case "HR":
                this.ratingsList.set(1, this.ratingsList.get(1) + 1);
                int incHR = Math.round(rating * (1 / this.ratingsList.get(1)));
                this.ratingsList.set(0, this.ratingsList.get(0) + incHR);
            case "Applicant":
                this.ratingsList.set(3, this.ratingsList.get(3) + 1);
                int incApp = Math.round(rating * (1 / this.ratingsList.get(3)));
                this.ratingsList.set(2, this.ratingsList.get(2) + incApp);
        }
    }

    private int getDiffInDays(Calendar cal) {
        Calendar currentCal = Calendar.getInstance();
        int i = 0;
        do {
            i += currentCal.compareTo(cal);
            currentCal.add(Calendar.DATE, -1);
        } while (currentCal.compareTo(cal) > 0);

        return i;
    }

    public void signUpForInterview(int num){  // ignore error handling, cuz assuming they can only choose interviews displayed above
        while (num != (-1)) {
            ArrayList<Interview> invitedInts = interviewMan.getInvitedInterviews();
            Interview interview = invitedInts.get(num-1);
            invitedInts.remove(interview);   //   move interview from invitedInts to currentInts
            interviewMan.addNewInterview(interview);
            interview.getInterviewers().add(this);  //update in Interview
            interview.getInterviewee().displayInterviewers(interview);  // notify Applicants
            System.out.println("Sign-Up completed!");
            num = interviewMan.displayInvitedInterviews();
        }
    }

    public void recommendApplicant(boolean recommendApp) {  // recommendApp == false means reject applicant
        System.out.println("______________________________");
        System.out.println("| Recommend/Reject Applicant |");
        System.out.println("|____________________________|\n");
        int recId = IBIO.inputInt("| Enter the ID of applicant (-1 to leave): ");
        while (recId != (-1)) {
            int jobId = IBIO.inputInt("| Enter the JobId whose " +
                    "interviewee is tha applicant (-1 to re-enter Applicant Id): ");
            while (jobId != (-1)) {
                Interview interview = interviewMan.getIntByJobIdAndAppId(jobId, recId);
                Applicant applicant = interview.getInterviewee();
                JobPosting job = company.getJobByID(jobId);    // get corresponding interview, applicant & jobPosting
                interview.setFinished(true);    // update in Interview
                interviewMan.recApplicant(interview, applicant, recommendApp);   // update in InterviewManager
                informHR(company.getHRdept(), job, applicant);
                jobId = -1;
                if (recommendApp) {System.out.println("| U have successfully recommended this applicant!"); }
                else {System.out.println("| U have rejected this applicant.");}
            }
            recId = IBIO.inputInt("| Enter the ID of applicant (-1 to leave): ");
        }
    }

    public void informHR(HRCoord hrCoord, JobPosting job, Applicant applicant) {
        hrCoord.getIntervieweeManager().recApp(job, applicant);
    }

    public void viewDocuments() {
        int appID = IBIO.inputInt("Enter the ID of an applicant to view: ");
        boolean found = false;
        for (Interview interview : interviewMan.getCurrentInterviews().keySet()) {
            Applicant a = interview.getInterviewee();
            if (appID == a.getUserID()) {
                found = true;
                FileManager fm = new FileManager();
                System.out.println("Now viewing file " + a.getCL() + ":");
                fm.viewDocument(a.getCL());
                System.out.println("Now viewing file " + a.getCV() + ":");
                fm.viewDocument(a.getCV());
            }
        }
        if (!found)
            System.out.println("Candidate not found. Please check their ID again.");

    }

    public void viewProfile() {
        System.out.println("__________________");
        System.out.println("| View Profile    |");
        System.out.println("|_________________|\n");
        System.out.println(this.toString());
        System.out.println("Ratings:   ratingGivenByHR: " + ratingsList.get(0) +
                "   ratingGivenByInterviewees: " + ratingsList.get(1) + "\n" +
                "\t\t\texperience(measured in #of daysAsInterviewer): " + ratingsList.get(2) +
                "  experience(measured in #of pastFinishedInterviews): " + ratingsList.get(3));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Name: " + this.getName() + "  ID: " + this.getUserID() + "  Company: " + this.company.getName() + "\n");
        return sb.toString();
    }

    //  Getters
    public String getCompanyName(){return this.company.getName();}

    public InterviewManager getIntManager() {return interviewMan; }

    public ArrayList<Integer> getRatingsList() {
        int experienceByDays = getDiffInDays(dateCreated);
        ratingsList.remove(2);
        ratingsList.add(2, experienceByDays);
        interviewMan.updateFinishedInterviews();
        ratingsList.remove(3);
        ratingsList.add(3, interviewMan.getFinishedInterviews().size());
        return ratingsList;
    }
}
