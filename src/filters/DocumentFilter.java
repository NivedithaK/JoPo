package src.filters;

import src.main.JobPosting;
import src.main.JobApplication;
import users.*;
import java.util.ArrayList;

public class DocumentFilter extends JobPostingFilter {
    public DocumentFilter() {
        super();
    }

    /**
     * Returns list of applicants' documents from all job postings
     * @param filterType CV, CL, or both
     * @param list list of job postings of the company
     * @return list of applicants' documents from all job postings
     */
    public ArrayList filter(String filterType, ArrayList list) {
        ArrayList temp = new ArrayList();
        String path;
        if (filterType.equalsIgnoreCase("all")) { // get both CVs and cover letters
            temp.add(filterOne(list, "CV"));
            temp.add(filterOne(list, "CL"));
        } else if (filterType.equalsIgnoreCase("cv")) {  // get CVs
            temp.add(filterOne(list, "CV"));
        } else if (filterType.equalsIgnoreCase("cl")) { // get cover letters
            temp.add(filterOne(list, "CL"));
        }
        return temp;
    }

    private ArrayList filterOne(ArrayList list, String fileType){   // helper method for looping through jobs/applications
        ArrayList temp = new ArrayList();
        String path;
        for (Object j : list) {
               for (JobApplication jobapp : ((JobPosting) j).getJobApplications()) {
                   path = jobapp.getDocuments().get(fileType);
               if (path != null) {
                   temp.add(path);
               }
            }
        }
        return temp;
    }

    /**
     * Returns list of applicants' documents by job posting ID
     * @param documentType CV, CL or both
     * @param jobID job ID of the jobs wanted
     * @param lst list of job postings of the company
     * @return list of applicants' documents
     */
    public ArrayList filter(String documentType, int jobID, ArrayList lst) {
        ArrayList temp = super.filter("id", jobID, lst); // temp is now a list of wanted job postings
        return filter(documentType, temp);
    }
}
