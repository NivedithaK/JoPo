package src.managers;
import src.main.Company;

import java.io.Serializable;
import java.util.ArrayList;

public class IDManager implements Serializable {

    int userIDCount;
    int jobPostIDCount;

    public IDManager(int numUsers, ArrayList<Company> companies) {
        userIDCount = 100000 + numUsers;
        int jobcount = 0;
        for (Company c: companies) {
            jobcount += c.getJobPostingsList().size();
        }
        jobPostIDCount = 100 + jobcount;
    }

    public int newUserID(){
        int newID = userIDCount;
        userIDCount++;
        return newID;
    }

    public int newPostID(){
        int newID = jobPostIDCount;
        jobPostIDCount++;
        return newID;
    }
}
