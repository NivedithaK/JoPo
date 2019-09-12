package src.main;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import users.*;
import java.util.Observable;

public class Interview extends Observable implements Serializable {

    private String status;
    private Calendar date;
    private Calendar nextIntDate;
    private Applicant interviewee;
    private JobPosting jobPosting;
    private boolean finished;
    private int numInterviewers;
    private ArrayList<Interviewer> interviewersList;

    public Interview(Applicant interviewee, String status, Calendar date, JobPosting job) {
        this.interviewee = interviewee;
        this.status = status;
        this.date = date;
        this.jobPosting = job;
        this.interviewersList = new ArrayList<>();
        this.numInterviewers = 1;
        this.finished = false;
    }

    /**
     * Sets this Interview's status to newStatus and notifies its Observers.
     *
     * @param newStatus This Interview's new Status.
     */
    public void updateStatus(String newStatus) {
        this.addObserver(interviewee);
        String oldStatus = status;
        status = newStatus;
        setChanged();

        SimpleDateFormat format = new SimpleDateFormat("EEEE, MMMM d, yyyy");
        String dateString = format.format(this.date.getTime());
        String nextIntDateString = format.format(this.nextIntDate.getTime());
        notifyObservers("|  Congrats! You've finished <" + oldStatus + "> on " + dateString +
                ".\n|  Next: <" + newStatus + "> on " + nextIntDateString);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(jobPosting.getID());
        return sb.toString();
    }

    public Interviewer getInterviewerByID(int id) {
        while (true) {
            for (Interviewer interviewer : interviewersList) {
                if (interviewer.getUserID() == id) {
                    return interviewer;
                }
            }
        }
    }

    // Getters:

    public String getStatus() {
        return status;
    }

    public boolean isFinished() {
        return finished;
    }

    public Calendar getNextIntDate() {
        return nextIntDate;
    }

    public JobPosting getJobPosting() {
        return jobPosting;
    }

    public int getNumInterviewers() {
        return numInterviewers;
    }

    public Applicant getInterviewee() {
        return interviewee;
    }

    public void setNumInterviewers(int num) {
        numInterviewers = num;
    }

    public ArrayList<Interviewer> getInterviewers() {
        return interviewersList;
    }

    public boolean hasEnoughInterviewers() {
        return (interviewersList.size() == numInterviewers);
    }

    // Setters:

    public void setNextIntDate(Calendar date) {
        nextIntDate = date;
    }

    public void setFinished(boolean isFinished) {
        this.finished = isFinished;
    }

    public void addInterviewer(Interviewer newInterviewer) {
        this.interviewersList.add(newInterviewer);
    }

}

