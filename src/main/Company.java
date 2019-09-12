package src.main;

import users.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class Company implements Serializable {

    private ArrayList<JobPosting> jobPostingsList;
    private String name;
    private ArrayList<Interviewer> interviewers;
    private HRCoord hrdept;
    private ArrayList<String> interviewTypes;

    public Company(String name) {
        this.name = name;
        this.interviewers = new ArrayList<>();
        this.interviewTypes = new ArrayList<>();
        this.jobPostingsList = new ArrayList<>();
    }

    public Interviewer getInterviewerByID(int id) {
        for (Interviewer interviewer : this.interviewers) {
            if (interviewer.getUserID() == id) {
                return interviewer;
            }
        }
        return null;
    }

    public JobPosting getJobByID(int postID) {
        for (JobPosting jobPost : this.jobPostingsList) {
            if (jobPost.getID() == postID)
                return jobPost;
        }
        System.out.println("| Error: Job not found.");
        return null;
    }

    public void receiveJobApplication(Applicant applicant, JobPosting job) {
        // create Interview & jobApplication and updates in Applicant:
        Calendar date = Calendar.getInstance();
        Interview interview = new Interview(applicant, "Submission", date, job);
        applicant.getAllInts().put(interview, "");
        // notify applicant about status & updates details of interview:
        String firstIntType = (String) job.getInterviewTypesList().get(0).get(1);
        Calendar nextIntDate = (Calendar) job.getInterviewTypesList().get(0).get(2);
        interview.setNextIntDate(nextIntDate);
        int numInterviewers = (int) job.getInterviewTypesList().get(0).get(3);
        interview.setNumInterviewers(numInterviewers);
        interview.updateStatus(firstIntType);
        // updates in jobPosting:
        job.getJobApplications().add(applicant.getAllApps().get(job));
        job.addInterview(applicant, interview);

    }

    public void printJobsNeedingInterviews() {
        System.out.println("_____________________________");
        System.out.println("| Jobs Needing Interviews    |");
        System.out.println("|____________________________|\n");
        for (JobPosting job : this.jobPostingsList) {
            // at least one applicant, not enough interviewers, not filled
            if (!job.getAppInterviewMap().isEmpty() && !job.hasEnoughInterviewers() && !job.isFilled())
                System.out.println(job.toString());
        }
    }

    public void printJobs() {
        for (JobPosting j : this.jobPostingsList) {
            System.out.println(j + "\n");
        }
        if (this.jobPostingsList.isEmpty())
            System.out.println("\n| No jobs available at this moment.");

    }

    // Getters

    public ArrayList<String> getInterviewTypes() {
        return this.interviewTypes;
    }

    public ArrayList<Interviewer> getInterviewers() {
        return this.interviewers;
    }

    public ArrayList<JobPosting> getJobPostingsList() {
        return this.jobPostingsList;
    }

    public String getName() {
        return this.name;
    }

    public HRCoord getHRdept() {
        return this.hrdept;
    }


    // Setters

    public void addInterviewTypes(String newType) {
        this.interviewTypes.add(newType);
    }

    public void setHRdept(HRCoord hrdept) {
        this.hrdept = hrdept;
    }

    public void addInterviewers(Interviewer newInt) {
        this.interviewers.add(newInt);
    }

    public void addJobPosting(JobPosting job) {
        this.jobPostingsList.add(job);
    }

    public void deleteJobPosting(JobPosting job) { this.jobPostingsList.remove(job); }

}
