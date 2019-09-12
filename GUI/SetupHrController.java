package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import src.main.Company;


public class SetupHrController {

    DataModel model;
    @FXML
    TextField addCompany;
    @FXML
    ChoiceBox<String> companySelect;
    @FXML
    ChoiceBox<String> roleSelect;

    public SetupHrController(DataModel model){
        this.model = model;
    }

    //called to render list of companies
    @FXML
    private void initialize(){
        for (String companyName: this.model.jobportal.getCompanies().keySet()){
            companySelect.getItems().add(companyName);
        }
    }

    @FXML
    void setUpHRSubmit(ActionEvent event)throws Exception{
        Company currentComp = addCompany();
        if (currentComp != null && !roleSelect.getSelectionModel().isEmpty()){
            if (roleSelect.getSelectionModel().getSelectedItem().equals("HR Coordinator")){
                this.model.setNewHRCoor(currentComp);
                this.model.jobportal.addUserToList(this.model.user);
                moveHRCoorMenu(event);
            }
            else if (roleSelect.getSelectionModel().getSelectedItem().equals("Interviewer")){
                this.model.setNewInterviewer(currentComp);
                this.model.jobportal.addUserToList(this.model.user);
                moveInterviewerMenu(event);
            }
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out the form", ButtonType.CLOSE);
            alert.showAndWait();
        }

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
    boolean checkCompany(){
        //return if HR enters correct info for company name
        return this.model.jobportal.getCompanies().containsKey(addCompany.getText());
    }

    Company addCompany(){
        if (companySelect.getSelectionModel().isEmpty() && !checkCompany()){
            Company company = new Company(addCompany.getText());
            this.model.jobportal.addCompany(company);
            return company;
        }
        return null;
    }


}
