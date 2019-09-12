package src.managers;

import src.main.JobPortal;

import java.io.*;

public class SerializerManager implements Serializable {

    private static final long serialVersionUID = 6529685098267757690L;

    public static void main(String[] args) {}

    public JobPortal readJobPortal() {
        try {
            FileInputStream fis = new FileInputStream("./phase2/save.txt");
            try {
                ObjectInputStream ois = new ObjectInputStream(fis);
                Object a = ois.readObject();
                return (JobPortal) a;
            }
            catch (EOFException e) {
                return (new JobPortal(null, null));
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void writeObject(Object object) {
        try (FileOutputStream fos = new FileOutputStream("./phase2/save.txt");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}