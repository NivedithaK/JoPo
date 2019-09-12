package GUI;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;


public class ViewInterviewerProfile {

    @FXML
    TextField interviewerName;
    @FXML
    TextField interviewerId;
    @FXML
    TextField interviewerCompany;
    @FXML
    TextField interviewerRatings;

    DataModel model;

    public ViewInterviewerProfile(DataModel model){
        this.model = model;
    }

    @FXML
    private void initialized(){
        setText();
    }

    void setText(){
        interviewerName.setText("Name:" + this.model.getUserInterviewer().getName());
        interviewerId.setText("Id:" + this.model.getUserInterviewer().getUserID());
        interviewerCompany.setText("Company: " + this.model.getUserInterviewer().getCompanyName());
        interviewerRatings.setText("Ratings:   RatingGivenByHR: " + this.model.getUserInterviewer().getRatingsList().get(0) +
                "   RatingGivenByInterviewees: " + this.model.getUserInterviewer().getRatingsList().get(1) +
                "   Experience: " + this.model.getUserInterviewer().getRatingsList().get(2));

    }
}
