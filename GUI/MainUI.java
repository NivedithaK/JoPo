package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import src.managers.SerializerManager;
import java.io.Serializable;


public class MainUI extends Application implements Serializable {

    Stage stage;
    DataModel model;
    SerializerManager sm;
    //JobPortal jp;

    public static void main(String[] args) {
        launch(args);
        new MainUI();
    }

    public MainUI() {
        this.sm = new SerializerManager();
        //this.jp = sm.readJobPortal();
        this.model = new DataModel(sm.readJobPortal());
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/Login.fxml"));
        loader.setController(new LoginController(this.model));
        Parent root = loader.load();
        primaryStage.setTitle("Login");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
        primaryStage.setOnCloseRequest(event -> {
            System.out.println("CLOSING");
            System.out.println(this.model.jobportal.getUserlist());
            this.sm.writeObject(this.model.jobportal);
        });
        this.stage = primaryStage;

    }
}