package src.filters;

import src.main.JobPosting;

import java.util.ArrayList;
import java.util.Calendar;

public class JobPostingFilter implements Filter {

    /**
     * Filter by tag, job name, or company name
     * @param filterString tag, job name, or company name
     * @param keyword any part of the name of the job, company, or a tag
     * @param list lsit of all job postings
     * @return
     */
    public ArrayList filter(String filterString, String keyword, ArrayList list) {
        ArrayList temp = new ArrayList();
        keyword = keyword.toLowerCase();

        if (filterString.equalsIgnoreCase("t")) {   // filter by tag
            for (Object j : list) {
                if (((JobPosting) j).getTags().equalsIgnoreCase(keyword) ||
                        (((JobPosting) j).getTags().toLowerCase()).contains(keyword))
                    temp.add(j);
            }
        } else if (filterString.equalsIgnoreCase("j")) {  // filter by job name
            for (Object j : list) {
                if (((JobPosting) j).getName().equalsIgnoreCase(keyword) ||
                        (((JobPosting) j).getName().toLowerCase()).contains(keyword))
                    temp.add(j);
            }
        } else if (filterString.equalsIgnoreCase("c")) {  // filter by company name
            for (Object j : list) {
                if (((JobPosting) j).getCompany().getName().equalsIgnoreCase(keyword) ||
                        (((JobPosting) j).getCompany().getName().toLowerCase()).contains(keyword))
                    temp.add(j);
            }
        } else if (filterString.equalsIgnoreCase("r")) { // filter by job requirements
            for (Object j : list) {
                ArrayList<String> reqs = ((JobPosting) j).getRequirements();
                for (String req : reqs) {
                    if ((req.equalsIgnoreCase(keyword) || (req.toLowerCase().contains(keyword)) &&
                            !temp.contains(j)))
                        temp.add(j);
                }
            }
        }
        return temp;
    }

    /**
     * Filter by jobID or close date
     * @param filterString either id or close date
     * @param keyword number of days till a job posting closes, or (part of) a job ID
     * @param list list of all job postings
     * @return list of wanted job postings
     */
    public ArrayList filter(String filterString, int keyword, ArrayList list) {
        ArrayList temp = new ArrayList<JobPosting>();
        Integer id = keyword;
        if (filterString.equalsIgnoreCase("i")) {  // filter by job ID
            for (Object j : list) {
                if (((JobPosting) j).getID() == keyword ||
                        ((Integer) (((JobPosting) j).getID())).toString().contains(id.toString())) {
                    temp.add(j);
                }
            }
        } else if (filterString.equalsIgnoreCase("d")) {  // filter by close date
            Calendar dateEntered = Calendar.getInstance();
            dateEntered.add(Calendar.DATE, keyword);
            for (Object j : list) {
                if (((JobPosting) j).getCloseDate().compareTo(dateEntered) <= 0) {
                    temp.add(j);
                }
            }
        }
        return temp;
    }
}
