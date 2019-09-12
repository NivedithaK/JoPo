package src.managers;

import src.main.Interview;
import src.main.JobPosting;
import users.Applicant;
import users.HRCoord;
import users.Interviewer;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class IntervieweeManager implements Serializable {
// IntervieweeManager is responsible for managing applicants(interviewees) for each HR

    private HRCoord hr;

    public IntervieweeManager(HRCoord hr) { this.hr = hr; }

    public void recApp(JobPosting job, Applicant applicant) {
        boolean appRecByAllInterviewers = false;
        Interview interview = job.getAppInterviewMap().get(applicant);
        int jobId = job.getID();
        for (Interviewer interviewer : interview.getInterviewers()) {
            InterviewManager intMan = interviewer.getIntManager();
            if (intMan.getRecApplicants().containsKey(jobId)){
                if(intMan.getRecApplicants().get(jobId).contains(applicant)) {appRecByAllInterviewers = true;}
                else {
                    appRecByAllInterviewers = false;
                    break;
                }
            }
        }
        if (appRecByAllInterviewers) {
            job.getRecommended().add(applicant);} // rec applicant only when all interviewers rec this applicant
        job.setRoundFinished(job.allInterviewsFinished());
        if (job.getRoundFinished()) {createNextInt(job);}
    }

    public void createNextInt(JobPosting job) {
        if (job.getRecommended().size() > 1) {
            if (job.getInterviewTypesList().size() != 1) {  // when it's not the last round of interview for job
                job.getInterviewTypesList().remove(0);
                dealWithRecApps(job);
                job.setRoundFinished(false); }} // updates in jobPosting
        else {
            Applicant applicant = job.getRecommended().get(0);
            Interview interview = job.getAppInterviewMap().get(applicant);
            applicant.getAllInts().remove(interview);           // notify applicant that he's hired
            applicant.getAllInts().put(interview, "| Congratulations! You've been hired for the job.");
            applicant.removeJob(job);  // move jobApplication from currentApp to pastApp
            job.setFilled(true);  // update in JobPosting
            job.addHiredApplicant(applicant);
            System.out.println("| Success! A new employee has been hired for " + job.getName() + ".");
        }
    }

    public void dealWithRecApps(JobPosting job) {   // notify applicants who enters next round
        ArrayList<Applicant> toRemove = new ArrayList<>();
        for (Applicant applicant : job.getRecommended()) {
            Interview interview = applicant.getInterviewByJob(job);
            // notify applicant about status & resets details of interview:
            interview.setFinished(false);
            interview.getInterviewers().clear();
            String nextIntType = (String) job.getInterviewTypesList().get(0).get(1);
            Calendar nextIntDate = (Calendar) job.getInterviewTypesList().get(0).get(2);
            interview.setNextIntDate(nextIntDate);
            int num = (int) job.getInterviewTypesList().get(0).get(3);
            interview.setNumInterviewers(num);
            interview.updateStatus(nextIntType);
            toRemove.add(applicant);  // clear job.recommendedList
            for (Interviewer interviewer : interview.getInterviewers()) {
                interviewer.getIntManager().getRecApplicants().get(job.getID()).remove(applicant);
            }
        }
        job.getRecommended().removeAll(toRemove);
    }
}

