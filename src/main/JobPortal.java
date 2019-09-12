package src.main;

import src.managers.*;
import users.*;
import utilities.IBIO;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class JobPortal implements Serializable {

    private HashMap<String, Company> companies;
    private HashMap<Integer, User> userlist;
    private IDManager idmanager;
    private HRManager hrmanager;
    private ApplicantManager appmanager;
    private InterviewerManager intmanager;
    private static final long serialVersionUID = 6529685098267757690L;
    public FilterManager filtermanager;

    public JobPortal(HashMap<String, Company> companies, HashMap<Integer, User> userlist) {
        if (userlist != null) {
            this.userlist = userlist;
            if (companies != null) {
                this.companies = companies;
            }
        }
        else {
            this.companies = new HashMap<>();
            this.userlist = new HashMap<>();
        }
        this.idmanager = new IDManager(this.userlist.size(), new ArrayList<>(this.companies.values()));
        this.hrmanager = new HRManager(this);
        this.appmanager = new ApplicantManager(this);
        this.intmanager = new InterviewerManager(this);
        this.filtermanager = new FilterManager(this);
    }

    public void runJobPortal() {
        boolean quit = false;
        while (!quit) {
            LoginManager login = new LoginManager(this);
            login.run();
            this.companies = login.companies;
            this.userlist = login.userlist;
            deleteOldDox(this.userlist);
            String currentUser = login.getCurrentUser();
            switch (currentUser) {
                case "Applicant":
                    Applicant user = login.getCurrentApplicant();
                    this.appmanager.menu(user);
                    break;
                case "Interviewer":
                    Interviewer interviewer = login.getCurrentInterviewer();
                    this.intmanager.menu(interviewer);
                    break;
                case "HRCoord":
                    HRCoord hruser = login.getCurrentHR();
                    this.hrmanager.menu(hruser);
                    break;
            }
            quit = IBIO.inputBoolean("Enter 'true' to exit program, or anything else to continue: ");
        }
    }

    /**
     * Returns the number of days since the last application of this applicant closed
     * @param a - an Applicant
     * @return number of days since last application closed
     */
    private int getDaysSinceLastAppClosed(Applicant a) {
        int numDays = 0;
        if (a.getPastApps().isEmpty())
            return 0;
        for (JobPosting jp : a.getAllApps().keySet()) {
            Calendar temp = Calendar.getInstance();
            temp.add(Calendar.DATE, numDays);
            if (temp.compareTo(jp.getCloseDate()) > 0)
                numDays = getDiffInDays(jp.getCloseDate(), Calendar.getInstance());
        }
        return numDays;
    }

    /**
     * Returns number of days passed since the entered date
     * @param cal a Calendar date to compare with today's date
     * @param currentCal today's date
     * @return the difference in days, positive if the entered date is in the past
     */
    private int getDiffInDays(Calendar cal, Calendar currentCal) {
        int i = 0;
        do {
            i += currentCal.compareTo(cal);
            currentCal.add(Calendar.DATE, -1);
        } while (currentCal.compareTo(cal) > 0);
        return i;
    }


    // Getters

    public JobPosting getJobByID(int id) {
        for (Company c : this.companies.values()) {
            if (c.getJobByID(id) != null)
                return c.getJobByID(id);
        }
        return null;
    }

    public HashMap<Integer, User> getUserlist() {
        return this.userlist;
    }

    public HashMap<String, Company> getCompanies() {
        return this.companies;
    }

    public ArrayList<JobPosting> getJobPostingList(){
        ArrayList<JobPosting> arr = new ArrayList<>();
        for(Company c: companies.values()){
            arr.addAll(c.getJobPostingsList());
        }
        return arr;
    }

    public IDManager getIdManager(){
        return this.idmanager;
    }

    // Setters

    public void addCompany(Company company) {
        String companyName = company.getName();
        if (!this.companies.containsKey(companyName)) {
            this.companies.put(companyName, company);
        }
    }

    public void addUserToList(User user){
        this.userlist.put(user.getUserID(), user);
    }

    private void deleteOldDox(HashMap<Integer, User> userList) {
        for (User u : userList.values()) {
            if (u instanceof Applicant) {
                int num = getDaysSinceLastAppClosed((Applicant) u);
                if (num >= 30) {
                    ((Applicant) u).setCL(null);
                    ((Applicant) u).setCV(null);
                }
            }
        }
    }
}

