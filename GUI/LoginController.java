package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.Serializable;

public class LoginController extends MainUI implements Serializable {

    DataModel model;
    @FXML TextField userIdInput;
    @FXML TextField passwordInput;
    String userid;
    String password;
    int id;

    public LoginController(DataModel model) {
        this.model = model;
    }

    private boolean isNumeric (String userid) {
        if (userid.matches("-?\\d+(\\.\\d+)?"))
            this.id = Integer.parseInt(this.userid);
        return userid.matches("-?\\d+(\\.\\d+)?");
    }

    @FXML
    private boolean loginUserSuccess() {
        if (isNumeric(this.userid) && this.model.userExists(this.id)) {
            if (this.model.correctPassword(this.id, this.password)) {
                this.model.setUser(this.model.jobportal.getUserlist().get(id));
                return true;
            }
        }
        return false;
    }

    @FXML
    void loginButton (ActionEvent event) throws Exception {
        this.userid = userIdInput.getText();
        this.password = passwordInput.getText();
        if (loginUserSuccess()) {
            switch (this.model.getUserType()) {
                case "Applicant":
                    moveToApplicantMenu(event);
                case "HRCoord":
                    moveHRCoorMenu(event);
                case "Interviewer":
                    moveInterviewerMenu(event);
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid User ID or Password", ButtonType.CLOSE);
            alert.showAndWait();
        }
    }

    @FXML
    void signUpButton(ActionEvent event) throws Exception{
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Signup.fxml"));
            loader.setController(new SignupController());
            Parent root = loader.load();
            Scene s = new Scene(root);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(s);
            window.show();
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

    void moveHRCoorMenu(ActionEvent event) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/HRCoordMenu.fxml"));
        loader.setController(new HRCoordMenuController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    void moveInterviewerMenu(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/InterviewerMenu.fxml"));
        loader.setController(new HRCoordMenuController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }


}
