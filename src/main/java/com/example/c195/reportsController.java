package com.example.c195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class reportsController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    TextField january;
    @FXML
    TextField february;
    @FXML
    TextField march;
    @FXML
    TextField april;
    @FXML
    TextField may;
    @FXML
    TextField june;
    @FXML
    TextField july;
    @FXML
    TextField august;
    @FXML
    TextField september;
    @FXML
    TextField october;
    @FXML
    TextField november;
    @FXML
    TextField december;
    @FXML
    TextField planningSession;
    @FXML
    TextField deBriefing;

    public Integer januaryApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("01")){
                count++;
            }
        }

        return count;
    }

    public Integer februaryApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("02")){
                count++;
            }
        }

        return count;
    }

    public Integer marchApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("03")){
                count++;
            }
        }

        return count;
    }

    public Integer aprilApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("04")){
                count++;
            }
        }

        return count;
    }

    public Integer mayApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("05")){
                count++;
            }
        }

        return count;
    }

    public Integer juneApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("06")){
                count++;
            }
        }

        return count;
    }

    public Integer julyApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("07")){
                count++;
            }
        }

        return count;
    }

    public Integer augustApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("08")){
                count++;
            }
        }

        return count;
    }

    public Integer septemberApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("09")){
                count++;
            }
        }

        return count;
    }

    public Integer octoberApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("10")){
                count++;
            }
        }

        return count;
    }


    public Integer novemberApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("11")){
                count++;
            }
        }

        return count;
    }


    public Integer decemberApps(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(5,7).equals("12")){
                count++;
            }
        }

        return count;
    }

    public Integer planningSessions(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getType().equals("Planning Session")){
                count++;
            }
        }

        return count;
    }

    public Integer deBriefingSessions(){
        Integer count = 0;
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getType().equals("De-Briefing")){
                count++;
            }
        }

        return count;
    }


    public void setAllFields(){
        january.setText(String.valueOf(januaryApps()));
        february.setText(String.valueOf(februaryApps()));
        march.setText(String.valueOf(marchApps()));
        april.setText(String.valueOf(aprilApps()));
        may.setText(String.valueOf(mayApps()));
        june.setText(String.valueOf(juneApps()));
        july.setText(String.valueOf(julyApps()));
        august.setText(String.valueOf(augustApps()));
        september.setText(String.valueOf(septemberApps()));
        october.setText(String.valueOf(octoberApps()));
        november.setText(String.valueOf(novemberApps()));
        december.setText(String.valueOf(decemberApps()));
        deBriefing.setText(String.valueOf(deBriefingSessions()));
        planningSession.setText(String.valueOf(planningSessions()));
    }

    @FXML
    public void switchToMainScreen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-screen.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setAllFields();
    }
}
