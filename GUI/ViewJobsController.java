package GUI;

import javafx.collections.FXCollections;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import src.main.*;

import java.util.ArrayList;

public class ViewJobsController extends MainUI {
    @FXML
    private TableView<JobPosting> tableView;
    @FXML
    private TableColumn<JobPosting, String> postingId;
    @FXML
    private TableColumn<JobPosting, String> postingCompany;
    @FXML
    private TableColumn<JobPosting, String> postingName;
    @FXML
    private TableColumn<JobPosting, String> postingOpenDate;
    @FXML
    private TableColumn<JobPosting, String> postingCloseDate;
    @FXML
    private TableColumn<JobPosting, String> postingRequirements;
    @FXML
    private TextField filterFieldTag;
    @FXML
    private TextField filterFieldName;
    @FXML
    private TextField filterFieldCompany;
    @FXML
    private TextField filterFieldRequirements;

    ArrayList<JobPosting> listings;
    DataModel model;
    FilteredList<JobPosting> filteredListing;
    SortedList<JobPosting> sortedListing;


    public ViewJobsController(DataModel model){
        this.model = model;
        this.listings = this.model.jobportal.getJobPostingList();
        System.out.println(this.listings.size());

    }

    @FXML
    private void initialize(){
        createTable();
        setFilterFieldTag();
        setFilterFieldName();
        setFilterFieldCompany();
        setFilterFieldRequirements();
    }

    @FXML
    void createTable(){
        postingId.setCellValueFactory(new PropertyValueFactory<JobPosting, String>("idString"));
        postingCompany.setCellValueFactory(new PropertyValueFactory<JobPosting, String>("companyName"));
        postingName.setCellValueFactory(new PropertyValueFactory<JobPosting, String>("name"));
        postingRequirements.setCellValueFactory(new PropertyValueFactory<JobPosting, String>("requirements"));
        postingOpenDate.setCellValueFactory(new PropertyValueFactory<>("openDateString"));
        postingCloseDate.setCellValueFactory(new PropertyValueFactory<>("closeDateString"));
        for (JobPosting jp: this.listings) {

            tableView.getItems().add(jp);
        }
        filteredListing = new FilteredList<>(FXCollections.observableArrayList(this.listings), p -> true);
        sortedListing = new SortedList<>(filteredListing);
        sortedListing.comparatorProperty().bind(tableView.comparatorProperty());
        tableView.setItems(sortedListing);
    }

    @FXML
    void setFilterFieldTag(){
        filterFieldTag.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListing.setPredicate(jobPosting -> {
                // If filter text is empty, display all listings.
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                return jobPosting.getTags().toLowerCase().contains(lowerCaseFilter);
                // Does not match.
            });

        });
    }

    @FXML
    void setFilterFieldName(){
        filterFieldName.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListing.setPredicate(jobPosting -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                return jobPosting.getName().toLowerCase().contains(lowerCaseFilter);
                // Does not match.
            });

        });
    }
    @FXML
    void setFilterFieldCompany(){
        filterFieldCompany.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListing.setPredicate(jobPosting -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                return jobPosting.getCompany().getName().toLowerCase().contains(lowerCaseFilter);
                // Does not match.
            });

        });

    }
    @FXML
    void setFilterFieldRequirements(){

        filterFieldRequirements.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredListing.setPredicate(jobPosting -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                return jobPosting.getRequirements().get(0).toLowerCase().contains(lowerCaseFilter); //TODO: HERE
                // Does not match.
            });

        });
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
