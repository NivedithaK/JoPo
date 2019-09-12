package src.managers;

import java.io.*;

public class FileManager implements Serializable  {

    public FileManager(){

    }

    public File saveFile(File file, int userid, String type, int jobid) {
        File newfile = new File("./phase2/users/applicant_files/" + userid + "_" + type + "_" + jobid + ".txt");
        if (!newfile.exists()) {
            try {
                newfile.createNewFile();
            } catch (IOException e) {
                System.out.println("Couldn't create file.");
            }
        }
        try {
            FileInputStream inStream = new FileInputStream(file);
            FileOutputStream outStream = new FileOutputStream(newfile);
            byte[] buffer = new byte[1024];
            int length;
            //copy the file content in bytes
            while ((length = inStream.read(buffer)) > 0) {
                outStream.write(buffer, 0, length);
            }

        } catch (Exception e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return newfile;

    }

    public void viewDocument(String path) {
        if (!path.equals("Expired.")) {
            File file = new File(path);
            try {
                FileInputStream input = new FileInputStream(file);
                int x;
                while ((x = input.read()) != -1) {
                    System.out.write(x);
                }
                System.out.flush();
            } catch (FileNotFoundException e1) {
                System.out.println("Something is wrong with the uploaded file path(s).");
            } catch (IOException e2) {
                System.out.println("File(s) cannot be read.");
            }
        } else {
            System.out.println(path);
            System.out.println("You may want to go back and resubmit your document(s).");
        }
    }

}
