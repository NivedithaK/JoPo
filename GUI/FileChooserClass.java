package GUI;

import java.awt.Desktop;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserClass {

    private Desktop desktop = Desktop.getDesktop();

    //open selected cv/cl, but will change open to something else later
    File onClick(Stage stage){
        final FileChooser fileChooser = new FileChooser();
        File file = fileChooser.showOpenDialog(stage);
//        if (file != null) {
//            saveFile(file, id, type);
//        }
        return file;
    }
    // move cv/cl?
    File saveFile(File file, int id, String type){
        File newfile = new File("./phase2/users/applicant_files/"+ id +"_" + type + ".txt");

        try {
            FileInputStream inStream = new FileInputStream(file);
            if(!newfile.exists()) {
                newfile.createNewFile();
            }
            FileOutputStream outStream = new FileOutputStream(newfile);
            byte[] buffer = new byte[1024];
            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

        }catch (Exception e){
         e.printStackTrace();
        }
        return newfile;

    }

    private void openFile(File file) {
        try {
            desktop.open(file);
        } catch (IOException ex) {
//            Logger.getLogger(
//                    FileChooserSample.class.getName()).log(
//                    Level.SEVERE, null, ex
//            );
        }
    }
}
