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

/**This is the controller for the schedule screen. */
public class contactScheduleController implements Initializable {
    /**This is the stage for this screen. */
    private Stage stage;
    /**This is the scene for this screen. */
    private Scene scene;
    /**This is the root for this screen. */
    private Parent root;
    /**This is the ObservableList for all of Anika's appointments. */
    public static ObservableList<Object> anikaAppointments = FXCollections.observableArrayList();
    /**This is the ObservableList for all of Daniel's appointments. */
    public static ObservableList<Object> danielAppointments = FXCollections.observableArrayList();
    /**This is the ObservableList for Li's appointments. */
    public static ObservableList<Object> liAppointments = FXCollections.observableArrayList();
    /**This is the tableview for Anika's appointments. */
    @FXML
    TableView<Object> anikaSchedule;
    /**This is the tableview for Daniel's appointments. */
    @FXML
    TableView<Object> danielSchedule;
    /**Thi is the tableview for Li's appointments. */
    @FXML
    TableView<Object> liSchedule;
    /**This is the Table column that represents the appointment ID. */
    @FXML
    TableColumn<appointment, Integer> anikaAppointmentID;
    /**This is the table colunn that represents the appointment title. */
    @FXML
    TableColumn<appointment, String> anikaAppointmentTitle;
    /**This is the table column that represents the appointment type. */
    @FXML
    TableColumn<appointment, String> anikaAppointmentType;
    /**This is the table column that represents the appointment description. */
    @FXML
    TableColumn<appointment, String> anikaAppointmentDescription;
    /**This is the table column that represents the appointment start date and time. */
    @FXML
    TableColumn<appointment, String> anikaAppointmentStart;
    /**This is the table column that represents the appointment end. */
    @FXML
    TableColumn<appointment, String> anikaAppointmentEnd;
    /**This is the table column that represents the customer ID. */
    @FXML
    TableColumn<appointment, Integer> anikaAppointmentCustomerID;
    /**This is the Table column that represents the appointment ID. */
    @FXML
    TableColumn<appointment, Integer> danielAppointmentID;
    /**This is the table colunn that represents the appointment title. */
    @FXML
    TableColumn<appointment, String> danielAppointmentTitle;
    /**This is the table column that represents the appointment type. */
    @FXML
    TableColumn<appointment, String> danielAppointmentType;
    /**This is the table column that represents the appointment description. */
    @FXML
    TableColumn<appointment, String> danielAppointmentDescription;
    /**This is the table column that represents the appointment start date and time. */
    @FXML
    TableColumn<appointment, String> danielAppointmentStart;
    /**This is the table column that represents the appointment end. */
    @FXML
    TableColumn<appointment, String> danielAppointmentEnd;
    /**This is the table column that represents the customer ID. */
    @FXML
    TableColumn<appointment, Integer> danielAppointmentCustomerID;
    /**This is the Table column that represents the appointment ID. */
    @FXML
    TableColumn<appointment, Integer> liAppointmentID;
    /**This is the table colunn that represents the appointment title. */
    @FXML
    TableColumn<appointment, String> liAppointmentTitle;
    /**This is the table column that represents the appointment type. */
    @FXML
    TableColumn<appointment, String> liAppointmentType;
    /**This is the table column that represents the appointment description. */
    @FXML
    TableColumn<appointment, String> liAppointmentDescription;
    /**This is the table column that represents the appointment start date and time. */
    @FXML
    TableColumn<appointment, String> liAppointmentStart;
    /**This is the table column that represents the appointment end. */
    @FXML
    TableColumn<appointment, String> liAppointmentEnd;
    /**This is the table column that represents the customer ID. */
    @FXML
    TableColumn<appointment, Integer> liAppointmentCustomerID;

    /**On the "back" button, this function takes you back to the main screen. */
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

    /**This initializes the tableviews and arraylists. */
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
    }
}
