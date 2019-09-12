package users;

import src.filters.DocumentFilter;
import src.main.*;
import src.main.InterviewArrangement.*;
import src.managers.IntervieweeManager;
import src.managers.JobPostingManager;
import utilities.IBIO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class HRCoord extends User implements Serializable {

    private Company company;
    private JobPostingManager jobManager;
    private IntervieweeManager intervieweeMan;

    public HRCoord(Company company, int id, String name, String password) {
        super(id, password, name);
        this.company = company;
        this.jobManager = new JobPostingManager(this);
        this.intervieweeMan = new IntervieweeManager(this);
    }

    public int menu() {
        System.out.println("______________________");
        System.out.println("| HR Coordinator Menu |");
        System.out.println("|_____________________|\n");
        System.out.println("| (1) Manage job postings");  // where HR add/delete jobs
        System.out.println("| (2) View all jobs at own company");
        System.out.println("| (3) View applicants' documents");
        System.out.println("| (4) Arrange interviews");
        System.out.println("| (5) Add new interview type for own company");
        System.out.println("| (6) Rate interviewers");
        System.out.println("| (7) Hire applicants");
        System.out.println("| (8) Generate interviewer account");
        System.out.println("| (9) Log out");
        return (IBIO.inputInt("| Enter a number: "));
    }

    public void addInterviewType() {
        String intType = IBIO.inputString("Enter an interview type for your company ('q' to quit): ");
        while (!intType.equals("q")) {
            this.company.addInterviewTypes(intType);
            intType = IBIO.inputString("Enter an interview type for your company ('q' to quit): ");
        }
    }

    public void arrangeInterviews () {
        this.company.printJobsNeedingInterviews();

        int jobid = IBIO.inputInt("| Enter job ID (-1 to quit): ");
        JobPosting job = this.company.getJobByID(jobid);
        if (jobid != -1 & job != null) {
            job.setClosed(true);
            int choice = (int) job.getInterviewTypesList().get(0).get(0);
            if (!job.getAppInterviewMap().isEmpty()) {
            switch (choice) {
                case 1://  HR assign
                    InterviewersGenerator<JobPosting> hrGenerator = new HRAssignGenerator();
                    hrGenerator.generate(job);
                    break;
                case 2: // interviewer self signUp
                    Calendar todayDate = Calendar.getInstance();
                    Calendar intDate = (Calendar) job.getInterviewTypesList().get(0).get(2);
                    if (todayDate.before(intDate)) {
                        InterviewersGenerator<JobPosting> signUpGenerator = new SelfSignUpGenerator();
                        signUpGenerator.generate(job);
                    } else {      // HR assign if there's not enough interviewer(s) until the date of interview
                        InterviewersGenerator<JobPosting> signUpGenerator = new HRAssignGenerator();
                        signUpGenerator.generate(job);
                    }
                    break;
                case 3: //  Auto selection from Roster, make sure # of interviewers needed <= # of total interviewers
                    InterviewersGenerator<JobPosting> rosterGenerator = new RosterGenerator();
                    rosterGenerator.generate(job);
                    break;
                case 4: //  Auto selection from Prioritized, make sure # of interviewers needed <= # of total interviewers
                    InterviewersGenerator<JobPosting> prioritizedGenerator = new PrioritizedGenerator();
                    prioritizedGenerator.generate(job);
                    break;
            }
            }
            else {System.out.println("| Error: No interviewees for this job.");}
        }
    }

    public void rateInterviewer() {
        System.out.println("____________________");
        System.out.println("| Rate Interviewer |");
        System.out.println("|__________________|\n");
        int id = IBIO.inputInt("| Enter the Interviewer ID (-1 to leave): ");
        while (id != -1) {
            Interviewer interviewer = company.getInterviewerByID(id);
            if (interviewer != null) {
                int rating = IBIO.inputInt("| Rate the interviewer from 1-10: ");
                if (rating >= 1 & rating <= 10) {
                    interviewer.addRating("HR", rating);
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

    public int hireForJob() {
        System.out.println("_________________________");
        System.out.println("| Hire Applicant For Jobs|");
        System.out.println("|________________________|\n");
        for (JobPosting job : this.company.getJobPostingsList()) {
            if (job.getInterviewTypesList().size() == 1 & job.getRoundFinished() & !job.finishedHiring()) {
                // display jobs which finishes the last round of interviews (all interviewers have finished and recommended/rejected applicants)
                // and don't display jobs which has finish hiring process
                StringBuilder sb = new StringBuilder();
                for (JobApplication jobapp : job.getJobApplications()) { sb.append(jobapp.getCurrentUser().toString()); }
                String allCandidatesStr = sb.toString();
                System.out.println("| JobID: " + job.getID() + "   Candidates: " + allCandidatesStr + "\n");
            }
        }
        System.out.println("| (-1) Finish");
        return (IBIO.inputInt("| Enter the JobID: "));
    }

    public void hireAppForJob(int jobID) {
        while (jobID != (-1)) {
            JobPosting job = company.getJobByID(jobID);
            if (job != null) {
                int applicantID = IBIO.inputInt("| Enter the ApplicantID to hire: ");
                Applicant applicant = job.getApplicantByID(applicantID);
                if (applicant != null) {
                    Interview interview = job.getAppInterviewMap().get(applicant);
                    applicant.getAllInts().remove(interview);           // notify applicant that he's hired
                    applicant.getAllInts().put(interview, "| Congrats! U've been hired for the job.");
                    applicant.removeJob(job);  // move jobApplication from currentApp to pastApp
                    job.setFilled(true);  // update in JobPosting
                    job.addHiredApplicant(applicant);
                    job.getJobApplications().remove(applicant.getAllApps().get(job));
                    // so that applicants who have been hired won't be displayed
                    System.out.println("| Success! A new employee has been hired for " + job.getName() + ".");
                }
            }
            jobID = hireForJob();
        }
    }

    public ArrayList filterApplicantDox(DocumentFilter doxFilter) {
        ArrayList temp = new ArrayList();
        for (JobPosting j : company.getJobPostingsList()) {
            temp.addAll(j.getJobApplications());
        }

        System.out.println("________________________");
        System.out.println("| Lookup Applicant Menu |");
        System.out.println("|_______________________|\n");
        System.out.println("| [a] to View all job Postings\n" +
                "| [id] Search for job postings by their IDs");
        String filterJobsBy = IBIO.inputString("| Please enter your choice: ");

        String documentType = IBIO.inputString("Which documents do you want to view?\n" +
                "[CV] View applicants' CV's\n[CL] View applicants' cover letters\n[both] View both\n" +
                "Please enter your choice: ");
        if (filterJobsBy.equalsIgnoreCase("id")) {
            int jobID = IBIO.inputInt("Enter the job ID to search for: ");
            return doxFilter.filter(documentType, jobID, temp);
        } else {
            return doxFilter.filter(documentType, temp);
        }
    }

    public Company getCompany() { return this.company; }

    public JobPostingManager getJobPostingManager() { return this.jobManager; }

    public IntervieweeManager getIntervieweeManager() { return intervieweeMan; }

    public void addInterviewType(String type) {
        this.company.addInterviewTypes(type);
    }

}


