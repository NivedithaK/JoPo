package src.main;

import src.managers.FileManager;
import src.managers.IDManager;
import users.*;
import utilities.IBIO;

import java.io.*;
import java.util.HashMap;

public class LoginManager implements Serializable {

    private User currentUser;
    HashMap<Integer, User> userlist;
    HashMap<String, Company> companies;
    private IDManager IDMan;

    public LoginManager(JobPortal jp) {
        this.companies = jp.getCompanies();
        this.userlist = jp.getUserlist();
        this.IDMan = jp.getIdManager();
        this.currentUser = null;
    }

    public void run() {
        if (!newUser()) {
            loginUser();
        } else {
            createNewUser();
        }
    }

    private boolean newUser() {
        printASCII();
        System.out.println("\nWelcome to the Job Portal!");
        char newUser;
        do {
            newUser = IBIO.inputChar("Are you a new user? (y/n) ");
            if (!(newUser == 'y' || newUser == 'n')) {
                System.out.println("Invalid input. Please try again.\n");
            }
        } while (!(newUser == 'y' || newUser == 'n'));
        return (newUser == 'y');
    }

    private void loginUser() {
        int userid = IBIO.inputInt("User ID (-1 to quit): ");
        while (userid != -1) {
            if (this.userlist.containsKey(userid)) {
                String password = IBIO.inputString("Password (type 'q' to quit): ");
                while (!password.equals("q")) {
                    if (this.userlist.get(userid).passwordCorrect(password)) {
                        updateCurrentUser(userid);
                        return;
                    }
                    password = IBIO.inputString("Password (type 'q' to quit): ");
                }
            }
            userid = IBIO.inputInt("User ID (-1 to quit): ");
        }
        this.run();
    }

    private void createNewUser() {
        System.out.println("______________________");
        System.out.println("| Create New Account |");
        System.out.println("|____________________|");
        int newId = this.IDMan.newUserID();
        String name = IBIO.inputString("\n| Please enter your name: ");
        char userstatus = IBIO.inputChar("| Are you an applicant (y/n)? ");
        while (!(userstatus == 'y' || userstatus == 'n')) {
            userstatus = IBIO.inputChar("| Please enter 'y' or 'n': ");
        }
        if (userstatus == 'y') {
            createApplicant(name, newId);
        } else {
            String companyName = IBIO.inputString("| Please enter your company: ");
            if (!companies.containsKey(companyName))
                createHRCoord(name, newId, companyName);
            else {
                System.out.println("| Error: This company already has an HR coordinator.");
                createNewUser();
            }
        }
    }

        private void createApplicant (String name,int id){
            System.out.println("\n| Your new user ID is: " + id);
            String password = IBIO.inputString("| Please enter a password: ");
            System.out.println("| A new account has been created!");
            System.out.println("\n______________________");
            System.out.println("| Set-Up New Account |");
            System.out.println("|____________________|");
            String CV = IBIO.inputString("| Write the path to your CV: ");
            String CL = IBIO.inputString("| Write the path to your cover letter: ");
            saveApplicantFiles(id, CV, CL);
            Applicant newApplicant = new Applicant(name, CV, CL, id, password);
            this.currentUser = newApplicant;
            userlist.put(id, newApplicant);
            System.out.println("| Set-up complete!\n");
        }

       private void saveApplicantFiles (int id, String CV, String CL) {
        File clfile = new File(CV);
        File cvfile = new File(CL);
        FileManager fm = new FileManager();
        fm.saveFile(cvfile, id,"CV", 0);
        fm.saveFile(clfile, id,"CL", 0);
        }

        private void createHRCoord (String name,int id, String companyName){
            Company c = new Company(companyName);
            this.companies.put(c.getName(), c);
            System.out.println("\n| Your new user ID is: " + id);
            String password = IBIO.inputString("| Please enter a password: ");
            HRCoord hrdept = new HRCoord(c, id, name, password);
            c.setHRdept(hrdept);
            hrdept.addInterviewType();
            while (c.getInterviewTypes().size() == 0) {
                System.out.println("[Error] You must add at least one interview type.");
                hrdept.addInterviewType();
            }
            this.currentUser = hrdept;
            userlist.put(id, hrdept);
        }

        // Getters

        public String getCurrentUser () {
            if (this.currentUser instanceof Applicant)
                return "Applicant";
            else if (this.currentUser instanceof Interviewer)
                return "Interviewer";
            else if (this.currentUser instanceof HRCoord)
                return "HRCoord";
            else
                return null;
        }

        public Applicant getCurrentApplicant () {
            return (Applicant) this.currentUser;
        }

        public Interviewer getCurrentInterviewer () {
            return (Interviewer) this.currentUser;
        }

        public HRCoord getCurrentHR () {
            return (HRCoord) this.currentUser;
        }

        private void updateCurrentUser ( int userid){
            this.currentUser = this.userlist.get(userid);
        }

        private void printASCII () {
            System.out.println("\n ______________");
            System.out.println("|\\ ___________ /|");
            System.out.println("| |  /|,| |   | |");
            System.out.println("| | |,x,| |   | |");
            System.out.println("| | |,x,' |   | |");
            System.out.println("| | |,x   ,   | |");
            System.out.println("| | |/    |%==| |");
            System.out.println("| |    /] ,   | |");
            System.out.println("| |   [/ ()   | |");
            System.out.println("| |       |   | |");
            System.out.println("| |       |   | |");
            System.out.println("| |       |   | |");
            System.out.println("| |      ,'   | |");
            System.out.println("| |   ,'      | |");
            System.out.println("|_|,'_________|_|");
        }

    }


