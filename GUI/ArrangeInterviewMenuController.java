package GUI;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ArrangeInterviewMenuController {
    DataModel model;
    List<String> interviewerSelected;
    int id;
    @FXML
    ListView interviewerList;


    public ArrangeInterviewMenuController(DataModel model, int id){
        this.model = model;
        this.id = id;
    }

    @FXML
    private void initialize(){
        setInterviewerList();
    }

    @FXML
    void backButton(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/HRCoordMenu.fxml"));
        loader.setController(new AddJobsController(this.model, new ArrayList<>()));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
    @FXML
    void onSubmit(ActionEvent event) throws Exception{
        //pass in selected one applicant + multiple interviewers selected
        //validate length of selected interviewers
        //return true if added (success message)

    }

    void setInterviewerList(){
        interviewerList.setItems(FXCollections.observableArrayList(this.model.getUserHRCoord().getCompany().getInterviewers()));
        interviewerList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        interviewerList.getSelectionModel().selectedItemProperty().addListener((obs,ov,nv)->{
            interviewerSelected.addAll(interviewerList.getSelectionModel().getSelectedItems());
        });
    }


}
