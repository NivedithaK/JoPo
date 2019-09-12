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

public class ViewCompanyJobsController {
    @FXML
    private TableView<JobPosting> tableView;
    @FXML
    private TableColumn<JobPosting, String> postingId;
    @FXML
    private TableColumn<JobPosting, String> postingName;
    @FXML
    private TableColumn<JobPosting, String> postingOpenDate;
    @FXML
    private TableColumn<JobPosting, String> postingCloseDate;
    @FXML
    private TableColumn<JobPosting, String> postingRequirements;

    DataModel model;

    public ViewCompanyJobsController(DataModel model){
        this.model = model;

    }

    @FXML
    private void initialize(){
        createTable();
    }

    @FXML
    void backButton(ActionEvent event) throws  Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/HRCoordMenu.fxml"));
        loader.setController(new HRCoordMenuController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
    void createTable(){
        postingId.setCellValueFactory(new PropertyValueFactory<JobPosting, String>("idString"));
        postingName.setCellValueFactory(new PropertyValueFactory<JobPosting, String>("name"));
        postingRequirements.setCellValueFactory(new PropertyValueFactory<JobPosting, String>("requirements"));
        postingOpenDate.setCellValueFactory(new PropertyValueFactory<>("openDateString"));
        postingCloseDate.setCellValueFactory(new PropertyValueFactory<>("closeDateString"));
        for (JobPosting jp: this.model.getUserHRCoord().getCompany().getJobPostingsList()) {
            tableView.getItems().add(jp);
        }
    }
}
