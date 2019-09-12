package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.main.*;

public class DisplayInvitedInterviews {
    @FXML
    TableView<Interview> interviews;
    @FXML
    TableColumn<Interview, String> interviewee;
    @FXML
    TableColumn<Interview, String> type;
    @FXML
    TableColumn<Interview, String> jobTitle;
    @FXML
    TableColumn<Interview, String> date;

    DataModel model;

    public DisplayInvitedInterviews(DataModel model){
        this.model = model;
    }

    void createTable(){
        interviewee.setCellValueFactory(new PropertyValueFactory<Interview, String>("intervieweeName"));
        type.setCellValueFactory(new PropertyValueFactory<Interview, String>("status"));
        jobTitle.setCellValueFactory(new PropertyValueFactory<Interview, String>("name"));
        date.setCellValueFactory(new PropertyValueFactory<Interview, String>("dateString"));
        for (Interview i: this.model.getUserInterviewer().getIntManager().getInvitedInterviews()) {
            interviews.getItems().add(i);
        }
    }
    @FXML
    void backButton(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ViewInterviewerProfile.fxml"));
        loader.setController(new ViewInterviewerProfile(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();

    }

    @FXML
    void onSubmit(){
        if(!interviews.getSelectionModel().isEmpty()){
//            this.model.getUserInterviewer().signUpForInterview(interviews.getSelectionModel().getSelectedItem());
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select an interview.", ButtonType.CLOSE);
            alert.showAndWait();
        }

    }

}
