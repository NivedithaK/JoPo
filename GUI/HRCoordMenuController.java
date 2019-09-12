package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Optional;

public class HRCoordMenuController {

    @FXML
    AnchorPane ap;
    @FXML
    Button addJobButton;
    DataModel model;

    public HRCoordMenuController(DataModel model){
        this.model = model;
    }

    @FXML
    void viewCompanyJobs(){

    }

    @FXML
    void addNewJob(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/AddJobs.fxml"));
        loader.setController(new AddJobsController(this.model, new ArrayList<>()));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }


    @FXML
    private void initialize() {
        setDisabled();
    }

    //set button disabled if current company does not have interview types
    void setDisabled() {
        if (this.model.getUserHRCoord().getCompany().getInterviewTypes().size()== 0){
            addJobButton.setDisable(true);


        }else{
            addJobButton.setDisable(false);
        }
    }

    @FXML
    void arrangeInterviews(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ArrangeInterviews.fxml"));
        loader.setController(new ArrangeInterviewsController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }

    @FXML
    void viewCompanyJobs(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ViewCompanyJobs.fxml"));
        loader.setController(new ViewCompanyJobsController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    @FXML
    void viewApplicantFiles(){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("View Documents");
        File defaultDirectory = new File("./phase2/users/applicant_files");
        chooser.setInitialDirectory(defaultDirectory);
        Stage stage = (Stage) ap.getScene().getWindow();
        File selectedDirectory = chooser.showDialog(stage);
    }

    @FXML
    void addNewInterviewType(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setTitle("Add new interview type");
        dialog.setContentText("Please enter the name of the interview type:");
        Optional<String> result = dialog.showAndWait();
        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(type ->{
            this.model.getUserHRCoord().addInterviewType(type);
            setDisabled();
        });
    }
    @FXML
    void hrLoggout(ActionEvent event) throws  Exception {
        this.model.setUser(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Login.fxml"));
        loader.setController(new LoginController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

}
