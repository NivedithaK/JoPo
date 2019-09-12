package src.main.InterviewArrangement;

import src.main.Interview;
import src.main.JobPosting;
import users.Applicant;
import users.Interviewer;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * A restriction on the type that can be used to instantiate a SelfSignUpGenerator: only those types T
 * that implement the interface InterviewersGenerator<T>.
 */
public class SelfSignUpGenerator implements InterviewersGenerator<JobPosting> {

    /**
     * Creates a new SelfSignUpGenerator.
     */
    public SelfSignUpGenerator() {
    }

    @Override
    public void generate(JobPosting job) {
        ArrayList<Interviewer> interviewers = job.getCompany().getInterviewers();
        HashMap<Applicant, Interview> appInterviewMap = job.getAppInterviewMap();
        for (Applicant applicant : appInterviewMap.keySet()) {
            Interview interview = appInterviewMap.get(applicant);
            for (Interviewer interviewer : interviewers) {
                interviewer.getIntManager().addInvitedInterview(interview);
            }
        }
        System.out.println("Interviewers are now able to sign up for interviews for this job.");
    }
}