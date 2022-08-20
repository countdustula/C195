package com.example.c195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class contactScheduleController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static ObservableList<Object> anikaAppointments = FXCollections.observableArrayList();
    public static ObservableList<Object> danielAppointments = FXCollections.observableArrayList();
    public static ObservableList<Object> liAppointments = FXCollections.observableArrayList();
    @FXML
    TableView<Object> anikaSchedule;
    @FXML
    TableView<Object> danielSchedule;
    @FXML
    TableView<Object> liSchedule;
    @FXML
    TableColumn<appointment, Integer> anikaAppointmentID;
    @FXML
    TableColumn<appointment, String> anikaAppointmentTitle;
    @FXML
    TableColumn<appointment, String> anikaAppointmentType;
    @FXML
    TableColumn<appointment, String> anikaAppointmentDescription;
    @FXML
    TableColumn<appointment, String> anikaAppointmentStart;
    @FXML
    TableColumn<appointment, String> anikaAppointmentEnd;
    @FXML
    TableColumn<appointment, Integer> anikaAppointmentCustomerID;
    @FXML
    TableColumn<appointment, Integer> danielAppointmentID;
    @FXML
    TableColumn<appointment, String> danielAppointmentTitle;
    @FXML
    TableColumn<appointment, String> danielAppointmentType;
    @FXML
    TableColumn<appointment, String> danielAppointmentDescription;
    @FXML
    TableColumn<appointment, String> danielAppointmentStart;
    @FXML
    TableColumn<appointment, String> danielAppointmentEnd;
    @FXML
    TableColumn<appointment, Integer> danielAppointmentCustomerID;
    @FXML
    TableColumn<appointment, Integer> liAppointmentID;
    @FXML
    TableColumn<appointment, String> liAppointmentTitle;
    @FXML
    TableColumn<appointment, String> liAppointmentType;
    @FXML
    TableColumn<appointment, String> liAppointmentDescription;
    @FXML
    TableColumn<appointment, String> liAppointmentStart;
    @FXML
    TableColumn<appointment, String> liAppointmentEnd;
    @FXML
    TableColumn<appointment, Integer> liAppointmentCustomerID;

    @FXML
    public void switchToMainScreen(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("main-screen.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
        anikaAppointments.clear();
        danielAppointments.clear();
        liAppointments.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getContact() == 1){
                anikaAppointments.add(appointment.allAppointmentsArrayList.get(i));
            }
            else if(appointment.allAppointmentsArrayList.get(i).getContact() == 2){
                danielAppointments.add(appointment.allAppointmentsArrayList.get(i));
            }
            else{
                liAppointments.add(appointment.allAppointmentsArrayList.get(i));
            }
        }



        anikaAppointmentID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("id"));
        anikaAppointmentTitle.setCellValueFactory(new PropertyValueFactory<appointment, String>("title"));
        anikaAppointmentType.setCellValueFactory(new PropertyValueFactory<appointment, String>("type"));
        anikaAppointmentDescription.setCellValueFactory(new PropertyValueFactory<appointment, String>("description"));
        anikaAppointmentStart.setCellValueFactory(new PropertyValueFactory<appointment, String>("start"));
        anikaAppointmentEnd.setCellValueFactory(new PropertyValueFactory<appointment, String>("end"));
        anikaAppointmentCustomerID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("customerID"));
        anikaSchedule.setItems(anikaAppointments);


        danielAppointmentID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("id"));
        danielAppointmentTitle.setCellValueFactory(new PropertyValueFactory<appointment, String>("title"));
        danielAppointmentType.setCellValueFactory(new PropertyValueFactory<appointment, String>("type"));
        danielAppointmentDescription.setCellValueFactory(new PropertyValueFactory<appointment, String>("description"));
        danielAppointmentStart.setCellValueFactory(new PropertyValueFactory<appointment, String>("start"));
        danielAppointmentEnd.setCellValueFactory(new PropertyValueFactory<appointment, String>("end"));
        danielAppointmentCustomerID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("customerID"));
        danielSchedule.setItems(danielAppointments);

        liAppointmentID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("id"));
        liAppointmentTitle.setCellValueFactory(new PropertyValueFactory<appointment, String>("title"));
        liAppointmentType.setCellValueFactory(new PropertyValueFactory<appointment, String>("type"));
        liAppointmentDescription.setCellValueFactory(new PropertyValueFactory<appointment, String>("description"));
        liAppointmentStart.setCellValueFactory(new PropertyValueFactory<appointment, String>("start"));
        liAppointmentEnd.setCellValueFactory(new PropertyValueFactory<appointment, String>("end"));
        liAppointmentCustomerID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("customerID"));
        liSchedule.setItems(liAppointments);

        System.out.println(anikaAppointments);
        System.out.println(danielAppointments);
        System.out.println(liAppointments);
    }
}
