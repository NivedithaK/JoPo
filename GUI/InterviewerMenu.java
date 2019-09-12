package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.File;
import java.util.Optional;

public class InterviewerMenu {

    DataModel model;
    @FXML
    AnchorPane ap;

    public InterviewerMenu(DataModel model){
        this.model = model;
    }

    @FXML
    void interviewerSignup(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/DisplayInvitedInterviewer.fxml"));
        loader.setController(new DisplayInvitedInterviews(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    @FXML
    void recommendApplicants(){
        // Create the custom dialog.
        Dialog<Pair<String, String>> dialog = new Dialog<>();
        dialog.setTitle("Recommend Applicant");
// Set the button types.
        ButtonType submitButton = new ButtonType("Submit", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(submitButton, ButtonType.CANCEL);

// Create the applicant id and job id fields
        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField applicantId = new TextField();
        TextField jobId = new TextField();

        grid.add(new Label("Enter the ID of an applicant to recommend:"), 0, 0);
        grid.add(applicantId, 1, 0);
        grid.add(new Label("Enter the JobId whose interviewee is the applicant:"), 0, 1);
        grid.add(jobId, 1, 1);

// Enable/Disable login button depending on whether a username was entered.
        Node submit = dialog.getDialogPane().lookupButton(submitButton);
        submit.setDisable(true);

// Do some validation (using the Java 8 lambda syntax).
        applicantId.textProperty().addListener((observable, oldValue, newValue) -> {
            submit.setDisable(newValue.trim().isEmpty());
        });
        jobId.textProperty().addListener((observable, oldValue, newValue) -> {
            submit.setDisable(newValue.trim().isEmpty());
        });
        dialog.getDialogPane().setContent(grid);

// Convert the result to a username-password-pair when the login button is clicked.
        dialog.setResultConverter(dialogButton -> {
            if (dialogButton == submitButton) {
                return new Pair<>(applicantId.getText(), jobId.getText());
            }
            return null;
        });

        Optional<Pair<String, String>> result = dialog.showAndWait();
        result.ifPresent(usernamePassword -> {
            // call interviewer recommend method to pass in appid and jobid, return true/false if added successfully.
//            System.out.println("Username=" + usernamePassword.getKey() + ", Password=" + usernamePassword.getValue());
        });
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
    void
    viewProfile(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ViewInterviewerProfile.fxml"));
        loader.setController(new ViewInterviewerProfile(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }


    @FXML
    void interviewerLogout(ActionEvent event) throws Exception{
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
