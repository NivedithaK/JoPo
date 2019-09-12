package src.managers;

import src.filters.JobPostingFilter;
import src.main.Company;
import src.main.JobPortal;
import utilities.IBIO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class FilterManager implements Serializable {

    JobPortal jp;

    public FilterManager(JobPortal jp) {
        this.jp = jp;
    }

    public void printJobs(ArrayList jobs) {
        System.out.println("________________________");
        System.out.println("| Filtered Job Postings |");
        System.out.println("|_______________________|\n");
        for (Object j : jobs) {
            System.out.println("\n" + j.toString() + "\n");
        }
    }

    public ArrayList filterJobs(JobPostingFilter jobFilter) {
        ArrayList allJobs = new ArrayList();

        for (Company c : this.jp.getCompanies().values()) {
            allJobs.addAll(c.getJobPostingsList());
        }

        String filterType = IBIO.inputString("How would you like to search?\n" +
                "[a] Get all job postings\n[t] Search by tag\n[j] Search by job name\n[c] Search by company name\n[r] Search by requirements\n" +
                "[i] Search by job ID\n[d] Search by close date\nPlease enter the corresponding letter: ");
        String[] options = {"a", "t", "j", "c", "r", "i", "d"};
        while (!Arrays.asList(options).contains(filterType)) {
            filterType = IBIO.inputString("Please enter one of the menu options: ");
        }
        if (filterType.equalsIgnoreCase("a")) {
            return allJobs;
        } else if (filterType.equalsIgnoreCase("i") || filterType.equalsIgnoreCase("d")) {
            int keyword = IBIO.inputInt("Enter the id or number of days till closing date: ");
            return jobFilter.filter(filterType, keyword, allJobs);
        } else {
            String keyword = IBIO.inputString("Enter search keyword: ");
            return jobFilter.filter(filterType, keyword, allJobs);

        }
    }
}