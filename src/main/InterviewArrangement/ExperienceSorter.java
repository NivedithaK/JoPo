package src.main.InterviewArrangement;

import java.util.ArrayList;

public class ExperienceSorter implements Sorter<users.Interviewer> {
    public ExperienceSorter() {}

    @Override
    public void sort(ArrayList<users.Interviewer> list) {
        for (int i = 0; i < list.size(); i++) {
            users.Interviewer temp = list.get(i);
            int j;
            for (j = i - 1; j >= 0 &&
                    temp.getIntManager().getFinishedInterviews().size() >
                            list.get(j).getIntManager().getFinishedInterviews().size(); j--) {
                list.set(j + 1, list.get(j));
            }
            list.set(j + 1, temp);
        }
    }
}
