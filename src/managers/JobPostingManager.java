package src.managers;

import src.main.Company;

import src.main.Interview;
import src.main.JobApplication;
import src.main.JobPosting;
import users.Applicant;
import users.HRCoord;
import utilities.IBIO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;

public class JobPostingManager implements Serializable {

    private HRCoord hr;

    public JobPostingManager(HRCoord hr) {
        this.hr = hr;
    }

    public void addJobPosting(int postID) {
        System.out.println("______________________");
        System.out.println("| Post a new job      |");
        System.out.println("|_____________________|\n");
        String jobName = IBIO.inputString("| Job title: ");
        ArrayList<String> jobReq = chooseRequirements();
        int jobDeadline = IBIO.inputInt("| Days until job posting closes: ");
        while (!(jobDeadline < 366 && jobDeadline > 0)) {
            jobDeadline = IBIO.inputInt("| [Error] Must be number from 1-365: ");
        }
        Calendar closeDate = Calendar.getInstance();
        closeDate.add(Calendar.DATE, jobDeadline);
        String tags = IBIO.inputString("| Tags for the job: ");
        int numPositions = IBIO.inputInt("| Number of positions: ");
        while (!(numPositions < 26 && numPositions > 0)) {
            numPositions = IBIO.inputInt("| [Error] Must be number from 1-25: ");
        }
        // 1. make sure U have added various interview types for company
        // 2. choose interview types according to the desired order
        ArrayList<ArrayList<Object>> intTypes = chooseInterviewTypes();
        //  3. for each interview type, decide how interviewers will be assigned
        //      & how many days until interview  &  how many interviewers needed
        JobPosting newJob = new JobPosting(jobName, jobReq, closeDate, postID, this.hr.getCompany(),
                intTypes, tags, numPositions);
        this.hr.getCompany().addJobPosting(newJob);
        System.out.println("| Success! Job has been added.");
    }

    public ArrayList<String> chooseRequirements() {
        System.out.println("| Enter job requirements one at a time (e.g. CV, CL).");
        ArrayList<String> jobReq = new ArrayList<>();
        String req = IBIO.inputString("| Requirements: ");
        while (!req.equals("q")) {
            jobReq.add(req);
            req = IBIO.inputString("| Requirements ('q' to quit): ");
        }
        return jobReq;
    }

    public ArrayList<ArrayList<Object>> chooseInterviewTypes() {
        ArrayList<ArrayList<Object>> interviewTypesList = new ArrayList<>();
        int choice = 0;
        while (choice != -1) {
            System.out.println("___________________________");
            System.out.println("| Choose Interview Types  |");
            System.out.println("|_________________________|\n");
            ArrayList<String> interviewTypes = this.hr.getCompany().getInterviewTypes();

            int i;
            for (i = 0; i < interviewTypes.size(); i++) {
                System.out.println("| (" + (i + 1) + ") " + interviewTypes.get(i));
            }
            System.out.println("| (-1) " + "Finish");
            choice = IBIO.inputInt("| Enter a number: ");
            if (choice >= 1 && choice <= interviewTypes.size()) {
                interviewTypesList.add(decideInterviewDetails(choice));
            }
            else {
                System.out.println("| Error: Interview type not found.");
            }
        }

        return interviewTypesList;
    }

    public ArrayList<Object> decideInterviewDetails(int num) {
        int type = selectInterviewers();
        while (type < 1 || type > 4) {
            System.out.println("| Error: Interviewer selection format not found.");
            type = selectInterviewers();
        }
        int interviewDeadline = IBIO.inputInt("| Days until this Interview: ");
        Calendar conductDate = Calendar.getInstance();
        conductDate.add(Calendar.DATE, interviewDeadline);
        int interviewerAmount = IBIO.inputInt("| How many interviewers needed: ");

        ArrayList<Object> interviewDetail = new ArrayList<>();
        interviewDetail.add(type);     // interviewer selection format
        interviewDetail.add(this.hr.getCompany().getInterviewTypes().get(num - 1)); // interview type
        interviewDetail.add(conductDate);      // conduct date
        interviewDetail.add(interviewerAmount);   // interviewer amount

        return interviewDetail;
    }

    public int selectInterviewers() {
        System.out.println("_______________________________");
        System.out.println("| Interviewer Selection Format |");
        System.out.println("|______________________________|\n");
        System.out.println("| (1) HR assign interviewers");
        System.out.println("| (2) Interviewers self sign-up");
        System.out.println("| (3) Auto selection from Roster");
        System.out.println("| (4) Auto selection from Prioritized");
        return (IBIO.inputInt("| Enter a number: "));
    }

    public void deleteJobPosting() {
        int jobId = IBIO.inputInt("| Enter the jobPosting ID: ");
        Company company = hr.getCompany();
        JobPosting job = company.getJobByID(jobId);
        if (job != null) {
            if (!job.isClosed()) {
                company.deleteJobPosting(job);
                for (JobApplication jobapp : job.getJobApplications()) {
                    for (JobApplication jA : jobapp.getCurrentUser().getCurrentApps()) {
                        if (jA.getJob().equals(job)) {
                            job.removeApplication(jA);
                        }
                    }
                    for (Interview interview : jobapp.getCurrentUser().getAllInts().keySet()) {
                        if (interview.getJobPosting().equals(job)) {
                            jobapp.getCurrentUser().getAllInts().remove(interview);
                        }
                    }
                }
            } else {
                System.out.println("| You are not allowed to modify the job since it's closed.");
            }
        }
    }

}
