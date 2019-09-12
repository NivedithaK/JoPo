package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.event.ActionEvent;
import java.io.File;
import java.io.Serializable;

public class SetupController implements Serializable {


    DataModel model;
    @FXML
    Label givenUserId;


    public SetupController(DataModel model){
        this.model = model;
    }

    @FXML private AnchorPane ap;

    @FXML
    void onClick(ActionEvent event){
        Stage stage = (Stage) ap.getScene().getWindow();
        FileChooserClass fcc = new FileChooserClass();
        Button button = (Button) event.getSource();
        File applicantFile =  fcc.onClick(stage);
        if (applicantFile != null){
            button.setText(applicantFile.getName());
            System.out.println(button.getId());
            if(button.getId().equals("setUpCl")){
                this.model.setCL(applicantFile.getName());
                fcc.saveFile(applicantFile, this.model.id, "cl");
            }else{
                fcc.saveFile(applicantFile, this.model.id, "cv");
                this.model.setCV(applicantFile.getName());

            }
        }

    }
    @FXML
    private void initialize() {
        givenUserId.setText("UserId: " + this.model.id);
    }

    @FXML
    void applicantSignup(ActionEvent event) throws Exception{
        createUser();
        if (this.model.user != null){
            System.out.println(this.model.jobportal.getUserlist());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Set-Up Complete");
            alert.initStyle(StageStyle.UNDECORATED);
            alert.setHeaderText(null);
            alert.setContentText("Set-Up Complete!");
            alert.showAndWait();
            moveToApplicantMenu(event);
        }
    }

    void moveToApplicantMenu(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ApplicantMenu.fxml"));
        loader.setController(new ApplicantMenuController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    void createUser(){
        System.out.println(this.model.cv);
        if(!this.model.cv.equals("") && !this.model.cl.equals("")){
            this.model.setNewApplicant();
            this.model.jobportal.addUserToList(this.model.user);
        }
    }
}
