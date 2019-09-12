package src.main.InterviewArrangement;

import java.util.ArrayList;

/**
 * A sorter that sorts list of interviewers based on their ratings
 */
public class RatingSorter implements Sorter<users.Interviewer> {

    public RatingSorter() {}

    /**
     * Sorts list using insertion sort
     * @param list
     */
    public void sort(ArrayList<users.Interviewer> list) {
        for (int i = 0; i < list.size(); i++) {
            users.Interviewer temp = list.get(i);
            int j = 0;
            for (j = i - 1; j >= 0 &&
                    calculateAverage(temp) > calculateAverage(list.get(j)); j--) {
                list.set(j + 1, list.get(j));
            }
            list.set(j + 1, temp);
        }
    }

    private int calculateAverage(users.Interviewer i) {
        int a = i.getRatingsList().get(0);
        int b = i.getRatingsList().get(2);
        return (a / 2) + (b / 2) + ((a % 2 + b % 2) / 2);
    }
}
