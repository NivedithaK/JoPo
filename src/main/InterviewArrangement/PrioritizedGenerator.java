package src.main.InterviewArrangement;

import src.main.Company;
import src.main.Interview;
import src.main.JobApplication;
import src.main.JobPosting;
import users.*;
import java.util.*;

/*
 * A restriction on the type that can be used to instantiate a PrioritizedGenerator: only those types T
 * that implement the interface InterviewersGenerator<T>.
 */
public class PrioritizedGenerator implements InterviewersGenerator<JobPosting> {

    /**
     * Creates a new PrioritizedGenerator.
     */
    public PrioritizedGenerator() {
    }

    @Override
    public void generate(JobPosting job) {
        Company company = job.getCompany();
        ArrayList<Interviewer> interviewers = company.getInterviewers();

        HashMap<Applicant, Interview> appInterviewMap = job.getAppInterviewMap();
        ArrayList<JobApplication> jAList = job.getJobApplications();
        int sortingCriteria = (new Random()).nextInt(2);
        Sorter sorter;
        if (sortingCriteria == 0) {
            sorter = new ExperienceSorter();
        } else {
            sorter = new RatingSorter();
        }
        sorter.sort(interviewers);
        for (JobApplication jobapp : jAList) {
            Interview interview = appInterviewMap.get(jobapp.getCurrentUser());

            // for each unassigned spot, choose the top interviewer that is not assigned to this interview yet
            int i = interview.getNumInterviewers() - interview.getInterviewers().size();
            for (; i > 0; i--) {
                int j = 0;
                // get the index of the first interviewer that has not yet been assigned to this interview
                while (j < interviewers.size() && interview.getInterviewers().contains(interviewers.get(j))) {
                    j++;
                }
                assignInterviewer(interviewers.get(j), interview);
            }
            jobapp.getCurrentUser().displayInterviewers(interview);
        }
        System.out.println("| Success! All applicants have been assigned interviewers for this job.\n" +
                "| Interviewers were auto-selected from prioritization by experience.");
    }

    private void assignInterviewer(Interviewer interviewer, Interview interview) {
        interview.addInterviewer(interviewer);
        interviewer.getIntManager().addNewInterview(interview);
    }
    //public ArrayList<Interviewer> OrderedInterviewersForCategory(int category, Company company) {}
}