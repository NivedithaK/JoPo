package src.main;

import src.managers.FileManager;
import utilities.IBIO;
import users.*;
import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.HashMap;

public class JobApplication implements Serializable {

    private Applicant currentUser;
    private HashMap<String, String> documents;
    private JobPosting job;
    private String status;
    private Calendar appDate;

    public JobApplication(Applicant user, JobPosting job) {
        this.currentUser = user;
        this.job = job;
        this.status = "submitted";
        this.appDate = Calendar.getInstance();
        this.documents = new HashMap<>();
        submitDocuments();
    }

    private void submitDocuments() {
        System.out.println("____________________");
        System.out.println("| SUBMIT DOCUMENTS |");
        System.out.println("|__________________|");
        for (String s : job.getRequirements()) {
            System.out.println("| Required: " + s);
            if (s.equals("CV") || s.equals("CL"))
                this.documents.put(s, chooseDocOrDefault(s));
            this.documents.put(s, chooseDoc(s));
        }
        updateDocuments();
        this.job.addApplication(this);
    }

    private String chooseDoc(String type) {
        return IBIO.inputString("Enter file path of " + type + ": ");
    }

    private String chooseDocOrDefault(String type) {
        int choice = IBIO.inputInt("(1) Use current " + type + " or (2) Choose new " + type + "? ");
        while (! (choice == 1 || choice == 2)) {
            choice = IBIO.inputInt("Please enter either 1 or 2: ");
        }

        if (choice == 1) {
            if (type.equals("CV"))
                return currentUser.getCV();
            return currentUser.getCL();
        }
        return chooseDoc(type);
    }

    private void updateDocuments() {
        FileManager fm = new FileManager();
        for (String a : documents.keySet()) {
            File newfile = new File(documents.get(a));
            fm.saveFile(newfile, currentUser.getUserID(), a, job.getID());
        }
    }

    // Getters

    public Applicant getCurrentUser(){return this.currentUser;}

    public JobPosting getJob() { return this.job; }

    public String getStatus() { return this.status; }

    public Calendar getAppDate() { return this.appDate; }

    public HashMap<String, String> getDocuments() {
        return documents;
    }
}
