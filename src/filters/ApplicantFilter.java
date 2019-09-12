package src.filters;

import users.*;
import java.util.ArrayList;

public class ApplicantFilter implements Filter {

    /**
     * Filter applicants by their names
     * @param filterType type of filter to be applied, i.e. name
     * @param keyword part of a name to be searching for
     * @param list list of applicants
     * @return list of applicants whose names contain the keyword
     */
    public ArrayList filter(String filterType, String keyword, ArrayList list){
        ArrayList<Applicant> temp = new ArrayList<Applicant>();
        keyword = keyword.toLowerCase();
        if (filterType.equalsIgnoreCase("name")) {  // filter by name
            for (Object a: list) {
                if (((Applicant) a).getName().toLowerCase().contains(keyword)) {
                    temp.add((Applicant) a);
                }
            }
        }
        return temp;
    }

    /**
     * Filter applicants by their IDs
     * @param filterType type of filter to be applied, i.e. ID
     * @param keyword the ID to be searching for
     * @param list list of all applicants
     * @return list of wanted applicants
     */
    public ArrayList filter(String filterType, int keyword, ArrayList list){
        ArrayList<Applicant> temp = new ArrayList<Applicant>();
        if (filterType.equalsIgnoreCase("id")) {  // filter by applicant ID
            for (Object a: list) {
                if (((Applicant) a).getUserID() == keyword) {
                    temp.add((Applicant) a);
                }
            }
        }
        return temp;
    }
}
