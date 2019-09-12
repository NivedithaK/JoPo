package users;

import java.io.Serializable;

public class User implements Serializable {
    private Integer userID;
    private String password;
    private String name;

    public User(int id, String password, String name) {
        this.userID = id;
        this.password = password;
        this.name = name;
    }

    // Setters

    public void changePassword(String oldPass, String newPass) {
        if (this.password.equals(oldPass)) {
            this.password = newPass;
            System.out.println("Password changed successfully!");
        }
        else
            System.out.println("Entered incorrect old password.");
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters

    public int getUserID() {
        return this.userID;
    }

    public String getName() { return this.name; }

    public String getPassword() { return password; }

    public boolean passwordCorrect(String password) { return this.password.equals(password); }
}