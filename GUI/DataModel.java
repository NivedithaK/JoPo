package GUI;

import src.main.Company;
import src.managers.IDManager;
import src.main.JobPortal;
import users.Applicant;
import users.HRCoord;
import users.Interviewer;
import users.User;
import java.io.Serializable;
import java.util.ArrayList;

public class DataModel implements Serializable {

    JobPortal jobportal;
    String name;
    String password;
    int id;
    User user;
    String cv;
    String cl;

    public DataModel(JobPortal jp) {
        this.jobportal = jp;
    }

    public boolean userExists (int id) {
        System.out.println(this.jobportal.getUserlist()); //TODO: over here
        return this.jobportal.getUserlist().containsKey(id);
    }

    public boolean correctPassword (int id, String password) {
        return this.jobportal.getUserlist().get(id).getPassword().equals(password);
    }

    // Setters

    public void setUser(User user) {
        this.user = user;
    }

    public void setNewApplicant() {
        this.user = new Applicant(this.name, cv,cl, id, password);
        addToUserlist();
    }

    public void setNewHRCoor(Company company) {
        this.user = new HRCoord(company, this.id, this.name, this.password);
        addToUserlist();
    }

    public void setNewInterviewer(Company company) {
        this.user = new Interviewer(company, this.id, this.name,this.password);
        addToUserlist();
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setCV(String cv) {
        this.cv = cv;
    }

    public void setCL(String cl) {
        this.cl = cl;
    }

    public void setID() {
        ArrayList<Company> tempCompanies = new ArrayList<>(this.jobportal.getCompanies().values());
        IDManager idm = new IDManager(this.jobportal.getUserlist().size(), tempCompanies);
        this.id = idm.newUserID();
    }

    public void addToUserlist() {
        this.jobportal.addUserToList(this.user);
    }

    // Getters

    public String getUserType() {
        if (this.user instanceof Applicant) {
            return "Applicant";
        } else if (this.user instanceof HRCoord) {
            return "HRCoord";
        } else {
            return "Interviewer";
        }
    }

    public Applicant getUserApplicant() {
        return (Applicant) this.user;
    }

    public HRCoord getUserHRCoord() {
        return (HRCoord) this.user;
    }

    public Interviewer getUserInterviewer() {
        return (Interviewer) this.user;
    }
}
