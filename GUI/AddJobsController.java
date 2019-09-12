package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.util.ArrayList;


public class AddJobsController {


    DataModel model;
    @FXML
    TextField addJobsTitle;
    @FXML
    TextField addJobsRequirements;
    @FXML
    TextField tagField;
    @FXML
    DatePicker closingDate;
    @FXML
    TextField numPosition;
    ArrayList<ArrayList<Object>> interviewTypes;

    public AddJobsController(DataModel model, ArrayList<ArrayList<Object>> interviewType){
        this.model = model;
        this.interviewTypes = interviewType;
    }


    @FXML
    void selectInterviewTypes(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/AddJobsInterviewTypes.fxml"));
        loader.setController(new AddJobsInterviewTypes(this.model,  ((Node)event.getSource()).getScene()));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        AddJobsInterviewTypes controller = loader.getController();
        this.interviewTypes = controller.getResult();
    }

    @FXML
    void backButton(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/HRCoordMenu.fxml"));
        loader.setController(new HRCoordMenuController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

    @FXML
    void addJobsSubmit(ActionEvent event) throws Exception {
        if (this.interviewTypes.size() == 0){
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please add interview types.", ButtonType.CLOSE);
            alert.showAndWait();
        }else{
//            this.model.getUserHRCoord().addJobPosting(this.model.jobportal.getIdManager().newPostID(),
//                    addJobsTitle.getText(), addJobsRequirements.getText(),
//                    closingDate.getValue(), this.model.getUserHRCoord().getCompany(), this.interviewTypes,
//                    tagField.getText(), numPosition.getText());
            FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/HRCoordMenu.fxml"));
            loader.setController(new HRCoordMenuController(this.model));
            Parent root = loader.load();
            Scene s = new Scene(root);
            Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
            window.setScene(s);
            window.show();
        }

    }


}
