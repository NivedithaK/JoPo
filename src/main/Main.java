package src.main;

import src.managers.SerializerManager;

import java.io.Serializable;

/** Main is the main class.
 * 1. Main creates an instance of SerializerManager
 * 2. SerializerManager reads save.txt and returns a saved JobPortal
 * 3. Main runs that JobPortal
 * 4. When the user quits the program, Main overwrites that saved JobPortal
 *    with the most recent one.
 */

public class Main implements Serializable {

    SerializerManager sm;
    JobPortal currentJobPortal;
//    private static final long serialVersionUID = 6529685098267757690L;

    public static void main(String[] args) {
        new Main();

//        new Thread() {
//            @Override
//            public void run() {
//                javafx.application.Application.launch(MainUI.class);
//
//            }
//        }.start();


    }

    public Main() {
        this.sm = new SerializerManager();
        this.currentJobPortal = sm.readJobPortal();
        currentJobPortal.runJobPortal();
        this.sm.writeObject(currentJobPortal);

    }
}
