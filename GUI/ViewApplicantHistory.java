package GUI;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.main.*;

public class ViewApplicantHistory {


    @FXML
    private TableView<JobApplication> historyView;
    @FXML
    private TableColumn<JobApplication, String> historyName;
    @FXML
    private TableColumn<JobApplication, String> historyCompany;
    @FXML
    private TableColumn<JobApplication, String> historyApplied;
    @FXML
    Label dateCreated;
    DataModel model;
    public ViewApplicantHistory(DataModel model ){
        this.model = model;
    }

    @FXML
    private void initialize(){
        dateCreated.setText(this.model.getUserApplicant().getAccountCreatedString());
        createTable();
    }

    @FXML
    void createTable(){
        historyName.setCellValueFactory(new PropertyValueFactory<>("name"));
        historyCompany.setCellValueFactory(new PropertyValueFactory<>("companyName"));
        historyApplied.setCellValueFactory(new PropertyValueFactory<>("appDateString"));
        for (JobApplication jobApp : this.model.getUserApplicant().getPastApps()) {
            historyView.getItems().add(jobApp);

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
