package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.main.*;

public class ViewApplicantStatus {
    @FXML
    private TableView<Interview> statusView;
    @FXML
    private TableColumn<Interview, String> statusName;
    @FXML
    private TableColumn<Interview, String> currentStatus;

    DataModel model;
    public ViewApplicantStatus(DataModel model ){
        this.model = model;
    }

    @FXML
    private void initialize(){
        createTable();
    }

    @FXML
    void createTable(){
        statusName.setCellValueFactory(new PropertyValueFactory<>("name"));
        currentStatus.setCellValueFactory(new PropertyValueFactory<>("appDateString"));

        for (Interview interview : this.model.getUserApplicant().getAllInts().keySet()) {
            statusView.getItems().add(interview);
        }

    }
    @FXML
    void backButton(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ApplicantMenu.fxml"));
        loader.setController(new ApplicantMenuController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }

}
