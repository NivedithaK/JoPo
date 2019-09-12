package users;

import src.main.*;
import src.managers.FileManager;
import src.managers.InterviewManager;
import utilities.IBIO;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Applicant extends User implements Serializable, Observer {

    private String CV;
    private String CL;
    private ArrayList<JobApplication> currentApps;
    private ArrayList<JobApplication> pastApps;
    private HashMap<Interview, String> allInts;
    private HashMap<JobPosting, JobApplication> allApps; // used when filtering applicant dox
    private Calendar accountCreated;

    public Applicant(String name, String CV, String CL, int id, String password) {
        super(id, password, name);
        this.CV = CV;
        this.CL = CL;
        this.accountCreated = Calendar.getInstance();
        this.currentApps = new ArrayList<>();
        this.pastApps = new ArrayList<>();
        this.allInts = new HashMap<>();
        this.allApps = new HashMap<>();
    }

    public boolean jobAppliedExists(int id) {
        for (JobApplication j : this.currentApps) {
            if (j.getJob().getID() == id) {
                return true;
            }
        }
        return false;
    }


    public int menu() {
        System.out.println("__________________");
        System.out.println("| Applicant Menu |");
        System.out.println("|________________|\n");
        System.out.println("| (1) View job postings");
        System.out.println("| (2) Apply to job posting by ID");
        System.out.println("| (3) View documents");
        System.out.println("| (4) View status of applications");
        System.out.println("| (5) View history");
        System.out.println("| (6) Rate interviewer");
        System.out.println("| (7) Log out");
//        deleteOldDox();
        return (IBIO.inputInt("| Enter a number: "));
    }

    public int apply() {
        return (IBIO.inputInt("Enter the job ID of the position you want to apply for (-1 to quit): "));
    }

    public boolean jobAlreadyExists(JobPosting job) {
        boolean jobExists = false;
        for (Interview interview : this.allInts.keySet()) {
            if (interview.getJobPosting().equals(job)) {
                jobExists = true;
            }
        }
        return jobExists;
    }

    public int docsMenu() {
        System.out.println("______________________");
        System.out.println("| View Documents      |");
        System.out.println("|_____________________|\n");
        System.out.println("| (1) View CV");
        System.out.println("| (2) View Cover letter");
        System.out.println("| (3) View both CV and Cover letter");
        System.out.println("| (4) Replace CV");
        System.out.println("| (5) Replace Cover letter");
        System.out.println("| (6) Return to menu");
        return (IBIO.inputInt("| Enter a number: "));
    }

    public void displayDox(int num) {
        if (num == 1) {
            viewDocument(CV);
        } else if (num == 2) {
            viewDocument(CL);
        } else if (num == 3) {
            viewDocument(CV);
            viewDocument(CL);
        } else if (num == 4) {
            CV = IBIO.inputString("| Write the path to your CV: ");
        } else if (num == 5) {
            CL = IBIO.inputString("| Write the path to your cover letter: ");
        }

    }

    public void viewDocument(String type) {
        FileManager fm = new FileManager();
        if (type.equals("CV"))
            fm.viewDocument(CV);
        else
            fm.viewDocument(CL);

    }

    public void viewStatus() {
        System.out.println("___________________________");
        System.out.println("| View Application Status  |");
        System.out.println("|__________________________|\n");
        for (Interview interview : this.allInts.keySet()) {
            System.out.println("| Job name: " + interview.getJobPosting().getName());
            System.out.println("| Application status:" + "\n" + allInts.get(interview) + "\n");
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        for (Interview interview : allInts.keySet()) {
            StringBuilder sb = new StringBuilder();
            sb.append(interview.getJobPosting().getID());
            String idString = sb.toString();        // o is identified as Observable object instead of Interview,
            // so comparing interview.jobPosting.id can make sure two interviews are identical
            if (o.toString().equals(idString)) {
                String status = (String) arg;
                allInts.put(interview, status);
            }
        }
    }

    public void displayInterviewers(Interview interview) {
        if (!interview.getInterviewers().isEmpty()) {
            String oldStatus = allInts.get(interview);
            StringBuilder sb = new StringBuilder(oldStatus);
            for (Interviewer interviewer : interview.getInterviewers()) {
                sb.append(interviewer.getName());
                sb.append("\t");
            }
            String newStatus = sb.toString();
            allInts.put(interview, newStatus);
        }
    }

    public void viewUserHistory() {
        System.out.println("______________________");
        System.out.println("| View User History   |");
        System.out.println("|_____________________|\n");

        System.out.println("| Account created on: " + getAccountCreatedString());
        System.out.println("| Previous applications: ");
        for (JobApplication jobApp : this.pastApps) {
            System.out.println("|  Name: " + jobApp.getJob().getName());
            System.out.println("|  Company: " + jobApp.getJob().getCompany());
            SimpleDateFormat dateformat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
            String applicationDate = dateformat.format(jobApp.getAppDate().getTime());
            System.out.println("|  Date applied: " + applicationDate);
        }
    }


    public void beginApplication(JobPosting jobPost) {
        JobApplication newJobApp = new JobApplication(this, jobPost);
        this.currentApps.add(newJobApp);
        this.allApps.put(jobPost, newJobApp);
//        String temp = null;
//        while (!(temp.equals("-1"))) {   //"Done" button not clicked.
//            temp = IBIO.inputString("Input file path: ");
        //newJobApp.getDocuments().add(temp); //TODO: OVER HERE
        //       }
    }

    public void removeJob(JobPosting jobPost) {
        ArrayList<JobApplication> toRemove = new ArrayList<>();
        for (JobApplication jobApp : this.currentApps) {
            if (jobApp.getJob().equals(jobPost)) {
                toRemove.add(jobApp);
                this.pastApps.add(jobApp);
            }
        }
        currentApps.removeAll(toRemove);
    }

    public void rateInterviewer() {
        System.out.println("____________________");
        System.out.println("| Rate Interviewer |");
        System.out.println("|__________________|\n");
        int id = IBIO.inputInt("| Enter the Interviewer ID (-1 to leave): ");
        while (id != -1) {
            String jobTitle = IBIO.inputString("| Enter the corresponding job title (-1 to leave): ");
            Interview interview = getIntByJobNameAndIntID(jobTitle, id);
            Company company = interview.getJobPosting().getCompany();
            Interviewer interviewer = company.getInterviewerByID(id);
            if (interviewer != null) {
                int rating = IBIO.inputInt("| Rate the interviewer from 1-10: ");
                if (rating >= 1 & rating <= 10) {
                    interviewer.addRating("Applicant", rating);
                    System.out.println("| Success! You have given a rating of " + rating + " to this interviewer!");
                }
                else
                    System.out.println("| Rating out of range [1, 10].");
            }
            else
                System.out.println("| No interviewer found.");

            id = IBIO.inputInt("| Enter the Interviewer ID (-1 to leave): ");
        }
    }

    public Interview getIntByJobNameAndIntID(String name, int interviewerID) {
        for (Interview interview : allInts.keySet()) {
            String jobName = interview.getJobPosting().getName();
            if (jobName.equals(name)) {
                ArrayList<Integer> idList = new ArrayList<>();
                for (Interviewer interviewer : interview.getInterviewers()) {
                    idList.add(interviewer.getUserID());
                }
                if (idList.contains(interviewerID)) {
                    return interview;
                }
            }
        }
        System.out.println("| Error: Interview with JobTitle and InterviewerID not found.");
        return null;
    }

    public Interview getInterviewByJob(JobPosting job) {
        for (Interview interview : allInts.keySet()) {
            if (interview.getJobPosting().equals(job)) {
                return interview;
            }
        }
        return null;
    }

    private void withdrawApp(JobPosting job) {
        boolean found = false;
        for (int i = 0; i < currentApps.size(); i++) {
            if ((currentApps.get(i).getJob()).equals(job)) {
                currentApps.set(i, null);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Application was not found under your account.");
        }
        found = false;
        for (int i = 0; i < job.getJobApplications().size(); i++) {
            if ((job.getJobApplications().get(i)).getCurrentUser().equals(this)) {
                job.getJobApplications().set(i, null);
                found = true;
            }
        }
        if (!found) {
            System.out.println("Application was not found under posting.");
            return;
        }
        System.out.println("Your application has been removed from the system");
    }

    public void removeApplication(JobPortal jobPortal, int jobID) {
        JobPosting jobPost = jobPortal.getJobByID(jobID);
        //Withdraws application at any point before posting is filled.
        //Check if Applicant is eligible to withdraw from this JobPosting.
        boolean found = false;
        JobApplication jobApp = null;
        for (JobApplication myJobApp : this.currentApps) {
            if (myJobApp.getJob().equals(jobPost)) {
                jobApp = myJobApp;
                found = true;
            }
        }
        if (!found) {
            //You do not have an application for this posting. Cannot withdraw.
            return;
        }
        if (jobPost.isFilled()){
            //You cannot withdraw from this posting, as you have either been hired/been rejected.
            return;
        }
        //JobPosting was applied to. Begin withdrawal process.
        jobPost.getJobApplications().remove(this.getAllApps().get(jobPost));
        jobPost.getRecommended().remove(this);
        jobPost.getRejected().remove(this);
        this.currentApps.remove(jobApp);
        this.pastApps.add(jobApp);
        for (Interview interview : this.allInts.keySet()) {
            if (interview.getJobPosting().equals(jobPost)) {
                if (interview.getInterviewee().equals(this)) {
                    this.allInts.remove(interview);
                    for (Interviewer interviewer : interview.getInterviewers()) {
                        interviewer.getIntManager().removeInterview(interview);
                    }
                    interview = null;
                }
            }
        }
    }

    public void clearOldDocuments() {
        if (this.pastApps.isEmpty()) {
            return;
        }
        int comparison = 0;
        int dateLimit = 30;
        boolean tooOld = true;
        for (JobApplication jA : this.pastApps) {
            comparison = jA.getAppDate().compareTo(Calendar.getInstance());
            if (comparison > dateLimit) {
                tooOld = false;
                break;
            }
        }
        if (tooOld) {
            this.CV = null;
            this.CL = null;
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("(ID: " + getUserID() + ") Name: " + getName());
        String result = sb.toString();
        return result;
    }


    // Getters

    public String getCV() {
        return this.CV;
    }

    public String getCL() {
        return this.CL;
    }

    public HashMap<JobPosting, JobApplication> getAllApps() {
        return this.allApps;
    }

    public ArrayList<JobApplication> getCurrentApps() {
        return this.currentApps;
    }

    public ArrayList<JobApplication> getPastApps() {
        return this.pastApps;
    }

    public HashMap<Interview, String> getAllInts() {
        return this.allInts;
    }

    public String getAccountCreatedString() {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        return sdf.format(this.accountCreated.getTime());

    }


    // Setters
    public void setCV(String newCV) {
        this.CV = newCV;
    }

    public void setCL(String newCL) {
        this.CL = newCL;
    }

}