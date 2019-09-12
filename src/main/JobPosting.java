package src.main;

import users.*;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class JobPosting implements Serializable {
    private String name;
    private int id;
    private boolean closed;
    private boolean filled;
    private Company company;
    private Calendar openDate;
    private final Calendar closeDate;
    private ArrayList<String> requirements;
    private int numPositions;
    private String tags;
    private ArrayList<JobApplication> jobApplications;
    private ArrayList<Applicant> recommended;
    private ArrayList<Applicant> rejected;
    private ArrayList<Applicant> hired;
    private ArrayList<ArrayList<Object>> interviewTypesList;
    // ^ [[1, "phone int", date, 2]] means phone interview is
    // the upcoming interview that will be held by 2 interviewers on date, and interviewers are assigned by HR
    private HashMap<Applicant, Interview> appInterviewMap;
    private boolean roundFinished;
    // roundFinished == true means all interviews of current round has finished,
    // and can move on to next round or hiring process


    public JobPosting(String name, ArrayList<String> requirements, Calendar closeDate, int id, Company company,
                      ArrayList<ArrayList<Object>> interviewTypes, String tags, int numPositions) {
        this.name = name;
        this.requirements = requirements;
        this.openDate = Calendar.getInstance();
        this.closeDate = closeDate;
        this.id = id;
        this.company = company;
        this.tags = tags;
        this.numPositions = numPositions;
        this.interviewTypesList = interviewTypes;
        this.jobApplications = new ArrayList<>();
        this.recommended = new ArrayList<>();
        this.rejected = new ArrayList<>();
        this.hired = new ArrayList<>();
        this.appInterviewMap = new HashMap<>();
        this.roundFinished = false;
        ArrayList<JobApplication> jobApplications = new ArrayList<>();
    }

    @Override
    public boolean equals(Object obj) {
        JobPosting job = (JobPosting) obj;
        return (this.id == job.getID());
    }

    public boolean hasEnoughInterviewers() {
        boolean haveEnoughInterviewers = false;
        for (JobApplication jobapp : jobApplications) {
            Interview interview = appInterviewMap.get(jobapp.getCurrentUser());
            haveEnoughInterviewers = interview.hasEnoughInterviewers();
        }
        return haveEnoughInterviewers;
    }

    public boolean finishedHiring() {
        return hired.size() == numPositions;
    }

    public boolean allInterviewsFinished() {
        boolean allInterviewsFinished = false;
        for (Interview interview : appInterviewMap.values()) {
            if (interview.isFinished()) {allInterviewsFinished = true;}
            else {
                allInterviewsFinished = false;
                break;
            }
        }
        return allInterviewsFinished;
    }

    public Applicant getApplicantByID(int id) {
        for (JobApplication jobapp: jobApplications) {
            if (jobapp.getCurrentUser().getUserID() == id) {
                return jobapp.getCurrentUser();
            }
        }
        System.out.println("| Error: Applicant not found.");
        return null;
    }

    public String toString() {
        SimpleDateFormat dateformat = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        String close = dateformat.format(this.closeDate.getTime());
        Calendar nextIntDate = (Calendar) interviewTypesList.get(0).get(2);
        String nextInt = dateformat.format(nextIntDate.getTime());
        int interviewersNum = (int) interviewTypesList.get(0).get(3);

        StringBuilder sb = new StringBuilder();
        sb.append("Company: " + this.company.getName() + "\nTitle: " + this.name + "\nID: "
                + this.id + "\nDeadline: " + close + "\nRequirements: " + this.requirements
                + "\nNumber of Applicants: " + appInterviewMap.keySet().size()
                + "\nNext Interview Date: " + nextInt +         //  HR can now see the deadline for next interview, so that they can arrange interviews before that
                ". Interview conducted by " + interviewersNum + " interviewer(s)" +
                " \nFilled: " + (filled) + "\n");
        if (filled) {
            sb.append("Hired applicant: ");
            for (Applicant applicant : hired) {
                sb.append(applicant.toString() + " ");
            }
        }
        return sb.toString();
    }

    // Getters:

    public ArrayList<JobApplication> getJobApplications() {
        return jobApplications;
    }

    public int getID() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean isFilled() {
        return filled;
    }

    public Company getCompany() {
        return company;
    }

    public Calendar getCloseDate() {
        return closeDate;
    }

    public ArrayList<String> getRequirements() {
        return requirements;
    }

    public ArrayList<Applicant> getRecommended() {
        return recommended;
    }

    public ArrayList<Applicant> getRejected() {
        return rejected;
    }

    public ArrayList<ArrayList<Object>> getInterviewTypesList() {
        return interviewTypesList;
    }

    public HashMap<Applicant, Interview> getAppInterviewMap() {
        return appInterviewMap;
    }

    public String getTags() {
        return tags;
    }

    public boolean getRoundFinished() {
        return roundFinished;
    }

    public ArrayList<Applicant> getHired() {
        return hired;
    }

    // Setters:

    public void addApplication(JobApplication a) {
        this.jobApplications.add(a);
    }

    public void setClosed(boolean newClosed) {
        this.closed = newClosed;
    }

    public void setFilled(boolean newFilled) {
        this.filled = newFilled;
    }

    public void addInterview(Applicant a, Interview i) {
        this.appInterviewMap.put(a, i);
    }

    public void setRoundFinished(boolean roundFinished) {
        this.roundFinished = roundFinished;
    }

    public void addHiredApplicant(Applicant newHired) {
        this.hired.add(newHired);
    }

    public void removeApplication(JobApplication ja) {
        this.jobApplications.remove(ja);
    }

    //Attributes were originally private with getters and setters (were removed), to control access.
    //Applicant List was originally JobApplication List, as relevant information was in JobApplication, not Applicant.
    //Methods to check closeDate/closed (was removed)/filled.
    //Attribute closeDate is meant to be final (was removed).
    //Attribute List of recommended Applicants (was removed). Methods to manage list of recommended applicants (add to, remove from).
}
