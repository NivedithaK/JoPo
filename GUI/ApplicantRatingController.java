package GUI;

import javafx.collections.FXCollections;
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

import java.util.ArrayList;
import java.util.List;


public class ApplicantRatingController {
    DataModel model;
    @FXML
    TextField interviewerId;
    @FXML
    TextField jobName;
    @FXML
    ChoiceBox<Integer> ratingSelect;

    public ApplicantRatingController(DataModel model){
        this.model = model;
    }

    @FXML
    private void initialize(){
        setRatingList();
    }

    @FXML
//    void onSubmit(){
//        if(!this.model.getUserApplicant().rateInterviewer(Integer.valueOf(interviewerId.getText()), jobName.getText(),
//                ratingSelect.getSelectionModel().getSelectedItem())){
//            Alert alert = new Alert(Alert.AlertType.ERROR, "Invalid interviewer id or job name. Please enter again.",
//                    ButtonType.CLOSE);
//            alert.showAndWait();
//
//        }
//    }

    void setRatingList(){
        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            list.add(i+1);
        }

        ratingSelect.setItems(FXCollections.observableArrayList(list));
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
