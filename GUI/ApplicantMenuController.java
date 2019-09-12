package GUI;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.AnchorPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import src.main.*;

import java.io.File;
import java.util.Optional;

public class ApplicantMenuController {

    DataModel model;

    public ApplicantMenuController(DataModel model){
        this.model = model;
    }

    @FXML
    AnchorPane ap;

    @FXML
    Label applicantID;

    @FXML
    private void initialize() {
        applicantID.setText("UserId: " + this.model.id);
    }

    @FXML
    void viewJobPostings(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ViewJobs.fxml"));
        loader.setController(new ViewJobsController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    @FXML
    void replaceDocuments(){

    }

    @FXML
    void viewDocuments(){
        DirectoryChooser chooser = new DirectoryChooser();
        chooser.setTitle("View Documents");
        File defaultDirectory = new File("./phase2/users/applicant_files");
        chooser.setInitialDirectory(defaultDirectory);
        Stage stage = (Stage) ap.getScene().getWindow();
        File selectedDirectory = chooser.showDialog(stage);
    }
    @FXML
    void applyJobs(){
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setTitle("Apply Job by Id");
        dialog.setContentText("Please enter the id of the job:");
        Optional<String> result = dialog.showAndWait();
        // The Java 8 way to get the response value (with lambda expression).
        result.ifPresent(id ->{JobPosting interestedJob = verifyAdd(id);
            if(interestedJob == null){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid job posting id", ButtonType.CLOSE);
                alert.showAndWait();
            }
            else{
                this.model.getUserApplicant().beginApplication(interestedJob);
            }
        } );
    }

    @FXML
    void viewStatus(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ViewApplicantStatus.fxml"));
        loader.setController(new ViewApplicantStatus(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }
    @FXML
    void viewHistory(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ViewApplicantHistory.fxml"));
        loader.setController(new ViewApplicantHistory(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    @FXML
    void withdraw() {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setHeaderText(null);
        dialog.setGraphic(null);
        dialog.setTitle("Withdraw application by job id");
        dialog.setContentText("Please enter enter the id of the job you would like to withdraw");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(id ->{
            if(!this.model.getUserApplicant().jobAppliedExists(Integer.valueOf(id))){
                Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid job posting id", ButtonType.CLOSE);
                alert.showAndWait();
            }
            else{
                this.model.getUserApplicant().removeApplication(this.model.jobportal, Integer.valueOf(id));
            }
        } );
    }

    @FXML
    void rateInterviewer(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ApplicantRating.fxml"));
        loader.setController(new ApplicantRatingController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();


    }

    @FXML
    void applicantLogout(ActionEvent event) throws Exception{
        this.model.setUser(null);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Login.fxml"));
        loader.setController(new LoginController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    //move to applicant
    JobPosting verifyAdd(String id){
        //validate input
        if (id.matches("-?(0|[1-9]\\d*)") && !id.equals("")) {
            //check all postings in jobportal if exist
            for (JobPosting jp : this.model.jobportal.getJobPostingList()) {
                //if posting exist
                if (jp.getID() == Integer.valueOf(id)) {
                    //verify applicant did not apply before
                    for (JobApplication applied : this.model.getUserApplicant().getCurrentApps()) {
                        // if applied before, return false
                        if (applied.getJob().getID() == Integer.valueOf(id)) {
                            return null;
                        }
                    }
                    //if applicant did not apply before, return true
                    return jp;
                }
            }
        }
        return null;
    }
}
