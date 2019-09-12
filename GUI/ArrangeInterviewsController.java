package GUI;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.util.Optional;

public class ArrangeInterviewsController{

    DataModel model;
    public ArrangeInterviewsController(DataModel model){
        this.model = model;
    }

    @FXML
    void arrangeButton(ActionEvent event){
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("Arrange interview: ");
        dialog.setContentText("Enter the job id to arrange interview: ");
        Optional<String> result = dialog.showAndWait();
        result.ifPresent(postingid -> {
//            if(this.model.getUserHRCoord().arrangeInterviews(postingid)){
//                try{
//                    hrAssign(event);
//                }catch (Exception e){
//                    throw new RuntimeException(e);
//                }
//            }else{
//                Alert alert = new Alert(Alert.AlertType.INFORMATION);
//                alert.setTitle("Set-Up Complete");
//                alert.initStyle(StageStyle.UNDECORATED);
//                alert.setHeaderText(null);
//                alert.setContentText("Set-Up Complete!");
//                alert.showAndWait();
//                try{
//                    moveHRMenu(event);
//                }catch (Exception e){
//                    throw new RuntimeException(e);
//                }
//            }
        });

    }

    void hrAssign(ActionEvent event, int id) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/ArrangeInterviewMenu.fxml"));
        loader.setController(new ArrangeInterviewMenuController(this.model, id));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
    void moveHRMenu(ActionEvent event) throws Exception{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FXML/HRCoordMenu.fxml"));
        loader.setController(new HRCoordMenuController(this.model));
        Parent root = loader.load();
        Scene s = new Scene(root);
        Stage window = (Stage) ((Node)event.getSource()).getScene().getWindow();
        window.setScene(s);
        window.show();
    }
}