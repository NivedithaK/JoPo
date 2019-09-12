package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class SignupController extends MainUI {

    String type;

    @FXML
    ChoiceBox<String> typeSelection;
    @FXML
    TextField signUpName;
    @FXML
    TextField signUpPassword;


    @FXML
    void signUpSubmit(ActionEvent event) throws Exception{
        //change scene depending on selected
        if (signUpName.getText().equals("") | signUpPassword.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out the form!", ButtonType.CLOSE);
            alert.showAndWait();
        }
        else if(typeSelection.getSelectionModel().getSelectedItem().equals("Applicant")){
            setUpModel();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/Setup.fxml"));
            loader.setController(new SetupController(this.model));
            Parent root = loader.load();
            Scene s = new Scene(root);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(s);
            window.show();
        }
        else if(typeSelection.getSelectionModel().getSelectedItem().equals("HR Coordinator/ Interviewer")){
            setUpModel();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/SetupHR.fxml"));
            loader.setController(new SetupHrController(this.model));
            Parent root = loader.load();
            Scene s = new Scene(root);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(s);
            window.show();
        }

    }

    void setUpModel(){
        this.model.setName(signUpName.getText());
        this.model.setPassword(signUpPassword.getText());
        this.model.setID();
    }

}
