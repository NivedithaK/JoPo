package src.main.InterviewArrangement;

import src.main.Company;
import src.main.Interview;
import src.main.JobApplication;
import src.main.JobPosting;
import users.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ThreadLocalRandom;

/*
 * A restriction on the type that can be used to instantiate a RosterGenerator: only those types T
 * that implement the interface InterviewersGenerator<T>.
 */

public class RosterGenerator implements InterviewersGenerator<JobPosting> {

    /**
     * Creates a new RosterGenerator.
     */
    public RosterGenerator() {
    }

    @Override
    public void generate(JobPosting job) {
        Company company = job.getCompany();
        ArrayList<Interviewer> interviewers = company.getInterviewers();
        HashMap<Applicant, Interview> appInterviewMap = job.getAppInterviewMap();
        ArrayList<JobApplication> jAList = job.getJobApplications();
        for (JobApplication jobapp : jAList) {
            Interview interview = appInterviewMap.get(jobapp.getCurrentUser());
            while (!interview.hasEnoughInterviewers()) {
                int randomIndex = ThreadLocalRandom.current().nextInt(0, interviewers.size());
                Interviewer interviewer = company.getInterviewers().get(randomIndex);
                assignInterviewer(interviewer, interview);
            }
            jobapp.getCurrentUser().displayInterviewers(interview);
        }
        System.out.println("| Success! All applicants have been assigned interviewer(s) for this job.\n" +
                "| Interviewers were auto-selected from a roster.");
    }

    public void assignInterviewer(Interviewer interviewer, Interview interview) {
        if (!interview.getInterviewers().contains(interviewer)){
            interview.getInterviewers().add(interviewer);
            interviewer.getIntManager().addNewInterview(interview);
        }
    }
}