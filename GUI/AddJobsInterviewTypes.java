package GUI;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Optional;

public class AddJobsInterviewTypes {

    DataModel model;
    ArrayList<ArrayList<Object>> interviewTypes;
    @FXML
    ChoiceBox<String> interviewTypeSelection;
    @FXML
    ChoiceBox<String> interviewerType;
    @FXML
    TextField interviewDays;
    @FXML
    TextField interviewerNeeded;
    @FXML
    Button submitButton;

    Scene prevScene;

    public AddJobsInterviewTypes(DataModel model, Scene prevScene){
        this.model = model;
        this.interviewTypes = new ArrayList<>();
        this.prevScene = prevScene;
    }


    @FXML
    private void initialize(){
        setInterviewTypes();
    }


    void setInterviewTypes(){
        interviewTypeSelection.setItems(
                FXCollections.observableArrayList(this.model.getUserHRCoord().getCompany().getInterviewTypes())
        );

    }


    //checks if form filled out
    boolean validateForm() {
        return interviewDays.getText().matches("-?(0|[1-9]\\d*)") && !interviewDays.getText().equals("") &&
                interviewerNeeded.getText().matches("-?(0|[1-9]\\d*)") && !interviewerNeeded.getText().equals("")&&
                !interviewTypeSelection.getSelectionModel().isEmpty() && !interviewerType.getSelectionModel().isEmpty();

    }

    @FXML
    void onSubmit(ActionEvent event) throws Exception{
        if (validateForm()){
            continueAdding(event);
            ArrayList<Object> arr = new ArrayList<>();
            arr.add(interviewTypeSelection.getSelectionModel().getSelectedItem());
            arr.add(interviewerType.getSelectionModel().getSelectedItem());
            arr.add(interviewDays.getText());
            arr.add(interviewerNeeded.getText());
            interviewTypes.add(arr);

        }
        else{
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please fill out the form", ButtonType.CLOSE);
            alert.showAndWait();
        }

    }
    ArrayList<ArrayList<Object>> getResult(){
        return this.interviewTypes;
    }

    void moveBackToMenu(ActionEvent event) throws Exception{
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(this.prevScene);
        window.show();
    }

    void continueAdding(ActionEvent event) throws Exception
    {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setContentText("Would you like to add more interview type?");
        ButtonType buttonTypeOne = new ButtonType("Yes");
        ButtonType buttonTypeTwo = new ButtonType("No");
        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == buttonTypeOne){
            resetFields();
        }
        else {
            moveBackToMenu(event);
        }

    }

    void resetFields(){
        interviewTypeSelection.getSelectionModel().clearSelection();
        interviewerType.getSelectionModel().clearSelection();
        interviewerNeeded.setText("");
        interviewDays.setText("");
    }

}
