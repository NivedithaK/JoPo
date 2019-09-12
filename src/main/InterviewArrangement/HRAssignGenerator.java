package src.main.InterviewArrangement;

import src.main.Company;
import src.main.Interview;
import src.main.JobApplication;
import src.main.JobPosting;
import users.*;
import utilities.IBIO;
import java.util.ArrayList;
import java.util.HashMap;

/*
 * A restriction on the type that can be used to instantiate a HRAssignGenerator: only those types T
 * that implement the interface InterviewersGenerator<T>.
 */
public class HRAssignGenerator implements InterviewersGenerator<JobPosting> {

    /**
     * Creates a new HRAssignGenerator.
     */
    public HRAssignGenerator() {}

    @Override
    public void generate(JobPosting job) {
        HashMap<Applicant, Interview> appInterviewMap = job.getAppInterviewMap();
        ArrayList<JobApplication> jAList = job.getJobApplications();

        System.out.println("_______________________");
        System.out.println("| Assign Interviewers  |");
        System.out.println("|______________________|\n");
        for (JobApplication jobapp: jAList) {
            Interview interview = appInterviewMap.get(jobapp.getCurrentUser());
            // only show applicants whose interview has not been assigned interviewers:
            if (!interview.hasEnoughInterviewers()) {
                System.out.println("| Applicant:\n");
                System.out.println("Name: " + jobapp.getCurrentUser().getName() + "\nInterview: "
                        + interview.getStatus() + "\nApplied job " + interview.getJobPosting().getName() + "\n");
                int id = chooseInterviewers(jobapp.getCurrentUser(), job);
                assignInterviewers(id, jobapp.getCurrentUser(), job);
                jobapp.getCurrentUser().displayInterviewers(interview);
                System.out.println("| Success! This applicant has been assigned interviewer(s).");
            }
        }
    }
    public int chooseInterviewers(Applicant applicant, JobPosting job){
        System.out.println("________________");
        System.out.println("| Interviewers |");
        System.out.println("|______________|\n");

        ArrayList<Interviewer> interviewers = job.getCompany().getInterviewers();
        for (Interviewer interviewer : interviewers) {  // when more than one interviewers are needed, interviewers who have already been selected will not
            // be shown cuz i.e. choosing interviewer1 twice != choosing 2 interviewers for an interview
            Interview appInterview = job.getAppInterviewMap().get(applicant);
            if (!appInterview.getInterviewers().contains(interviewer)) {
                System.out.println("| " + interviewer.toString());
            }
        }
        System.out.println("| (-1) " + "Finish");
        return (IBIO.inputInt("| Enter Interviewer ID (-1 to finish): "));
    }

    public void assignInterviewers(int id, Applicant applicant, JobPosting job) {
        int numInterviewers = 0;
        while (id != (-1)) {
            // update in Interview & JobPosting & Applicant:
            Company company = job.getCompany();
            Interviewer interviewer = company.getInterviewerByID(id);
            Interview interview = job.getAppInterviewMap().get(applicant);
            if (!interview.getInterviewers().contains(interviewer))   // assume they can only choose interviewers displayed above
                interview.getInterviewers().add(interviewer);
            // update in Interviewer:
            interviewer.getIntManager().addNewInterview(interview);
            // continue choosing interviewers if more interviewers are needed
            numInterviewers += 1;
            if (numInterviewers < interview.getNumInterviewers()) {id = chooseInterviewers(applicant, job);
            }
            if (numInterviewers >= interview.getNumInterviewers()) {
                break;
            }

        }
    }
}
