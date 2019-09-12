package src.main.InterviewArrangement;

public interface InterviewersGenerator<T> {

    /** Assign interviewers for the upcoming interview for a particular JobPosting. */
    void generate(T job);
}
