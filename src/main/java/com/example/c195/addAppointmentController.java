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
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimeZone;



/** This is the controller for the "Add Appointment" screen. */
public class addAppointmentController implements Initializable {
    public static ObservableList<Object> times2 = FXCollections.observableArrayList();
    /**This is the stage for the screen that is shown. */
    private Stage stage;
    /**This is the scene for the screen that is shown. */
    private Scene scene;
    /**This is the root for the screen that is shown. */
    private Parent root;
    /**This is the cancel button to go back a screen. */
    @FXML
    Button cancel;
    /**This is the submit button to create an appointment. */
    @FXML
    Button submit;
    /**This is the textfield that the user enters the title into. */
    @FXML
    TextField title;
    /**This is the textfield that the user enters the description into. */
    @FXML
    TextField description;
    /**This is the textfield that the user enters the location into.*/
    @FXML
    TextField location;
    /**This is the combobox where the user selects the contact. */
    @FXML
    ComboBox<Object> contact;
    /**This is the combobox where the user selects the type of appointment it is. */
    @FXML
    ComboBox<Object> type;
    /**This is the datepicker button where the user selects the date. */
    @FXML
    DatePicker date;
    /**This is the combobox where the user selects the start time for the appointment. */
    @FXML
    ComboBox start;
    /**This is the combobox where the user selects the end time for the appointment. */
    @FXML
    ComboBox end;
    /**This is the combobox where the user selects the customer ID associated with the appointment. */
    @FXML
    ComboBox customerID;
    /**This is the combobox where the user selects the associated userID. */
    @FXML
    ComboBox userID;
    /**This is the observablelist that holds all the contacts. */
    public static ObservableList<Object> contacts = FXCollections.observableArrayList();
    /**This is the observablelist that holds all of the times. */
    public static ObservableList<Object> times = FXCollections.observableArrayList();
    /**This is the observableList that holds all of the userID's. */
    public static ObservableList<Object> userIDList = FXCollections.observableArrayList();
    /**This is the observablelist that holds all the types of appointments */
    public static ObservableList<Object> types = FXCollections.observableArrayList();
    /**This is the date time formatter that formats the date so it corresponds with the database and other aspects of the program. */
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    /**This is the function that is associated with the cancel button. */
    @FXML
    public void cancel(ActionEvent actionEvent) throws IOException {
        contacts.clear();
        types.clear();
        times.clear();
        times2.clear();

        root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This is the function that checks if the proposed appointment times overlap with another one. */
    public boolean timeOverlaps(){
        ArrayList<String> usedTimes = new ArrayList<>();
        ArrayList<String> timesInbetween = new ArrayList<>();
        ArrayList<String> placeHolder = new ArrayList<>();

        for(int i=0; i<appointment.getAllAppointments().size(); i++){
            int start = Integer.valueOf(appointment.allAppointmentsArrayList.get(i).getUTCStart().substring(11, 13));
            int end = Integer.valueOf(appointment.allAppointmentsArrayList.get(i).getUTCEnd().substring(11, 13));

            for(int j = start; j <= end; j++){
                String example;
                if(j < 10){
                    example = appointment.allAppointmentsArrayList.get(i).getUTCStart().substring(0, 10) + " " + "0" + j + ":00:00";
                }
                else{
                    example = appointment.allAppointmentsArrayList.get(i).getUTCEnd().substring(0, 10) + " " + j + ":00:00";
                }
                usedTimes.add(example);
            }
        }

            int startTime = Integer.valueOf(start.getValue().toString().substring(0, 2));
            int endTime = Integer.valueOf(end.getValue().toString().substring(0, 2));

            for(int j = startTime; j <= endTime; j++){
                String example;
                if(j < 10){
                    example = date.getValue().toString() + " " + "0" + j + ":00:00";
                }
                else{
                    example = date.getValue().toString() + " " + j + ":00:00";
                }
                timesInbetween.add(example);
                placeHolder.add(example);
            }

        placeHolder.retainAll(usedTimes);

        if(!placeHolder.isEmpty()){
            return true;
        }

        else{
            return false;
        }
    }


    /**This is the function that checks if the proposed appointment times make sense.  This is in the sense of if the end time comes after the start. */
    public boolean doTimesMakeSense(){
        Integer beginning = times2.indexOf(start.getValue());
        Integer ending = times2.indexOf(end.getValue());



        if(beginning < ending){
            return true;
        }
        else{
            return false;
        }
    }

    /**This is the function that is associated with the submit button. */
    @FXML
    public void submit(ActionEvent actionEvent) throws IOException, SQLException {
        if(title.getText().isEmpty() || description.getText().isEmpty() || location.getText().isEmpty() ||
            contact.getValue() == null || type.getValue() == null || start.getValue() == null||
            end.getValue() == null|| customerID.getValue() == null || userID.getValue() == null || date.getValue() == null){
            MyInterface myInterface = (x, y, z) -> {
                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(x);
                alert.setHeaderText(y);
                alert.setContentText(z);
                alert.showAndWait();
            };
            myInterface.alert("Fields Empty", "One or more fields are empty.","Please fill out all fields and try again.");
        }

        else if(!doTimesMakeSense()){
            loginController.showAlert("The start of the meeting and end are not valid.", "The beginning of the meeting must be before the end.", "Please make the start of the meeting before the ending and try again.");
        }

        else if(timeOverlaps() == true){
            loginController.showAlert("Times overlap.", "There is a conflict with the appointment time.", "The appointment time and date overlaps.  Check available hours and dates and try again.");
        }
        else {

            int startYear = Integer.valueOf(date.getValue().getYear());
            int startMonth = Integer.valueOf(date.getValue().getMonthValue());
            int startDay = Integer.valueOf(date.getValue().getDayOfMonth());
            int startHour = Integer.valueOf(start.getValue().toString().substring(0, 2));

            LocalDate startDate = LocalDate.of(startYear, startMonth, startDay);
            LocalTime startTime = LocalTime.of(startHour, 00,00);
            ZonedDateTime startZDT = ZonedDateTime.of(startDate, startTime, ZoneId.of(TimeZone.getDefault().getID()));
            Instant startInstant = startZDT.toInstant();
            ZoneId localZoneId = ZoneId.of("UTC");
            ZonedDateTime startUTCtoLocal = startInstant.atZone(localZoneId);
            String startDateTime = String.valueOf(startUTCtoLocal.toLocalDate()) + " " + String.valueOf(startUTCtoLocal.toLocalTime());


            int endYear = Integer.valueOf(date.getValue().getYear());
            int endMonth = Integer.valueOf(date.getValue().getMonthValue());
            int endDay = Integer.valueOf(date.getValue().getDayOfMonth());
            if(Integer.valueOf(times2.get(0).toString().substring(0, 2)) > Integer.valueOf(Integer.valueOf(end.getValue().toString().substring(0, 2)))){
                endDay = date.getValue().plusDays(1).getDayOfMonth();
                endMonth = date.getValue().plusDays(1).getMonthValue();
                endYear = date.getValue().plusDays(1).getYear();
            }





            int endHour = Integer.valueOf(end.getValue().toString().substring(0, 2));

            LocalDate endDate = LocalDate.of(endYear, endMonth, endDay);
            LocalTime endTime = LocalTime.of(endHour, 00,00);
            ZonedDateTime endZDT = ZonedDateTime.of(endDate, endTime, ZoneId.of(TimeZone.getDefault().getID()));
            Instant endInstant = endZDT.toInstant();
            ZonedDateTime endUTCtoLocal = endInstant.atZone(localZoneId);
            String endDateTime = String.valueOf(endUTCtoLocal.toLocalDate()) + " " + String.valueOf(endUTCtoLocal.toLocalTime());


            String startSTRING = startDateTime;
            String endSTRING = endDateTime;
            Integer contactID = null;
            if(contact.getValue().toString().contentEquals("Anika Costa")){contactID = 1;}
            if(contact.getValue().toString().contentEquals("Daniel Garcia")){contactID = 2;}
            if(contact.getValue().toString().contentEquals("Li Lee")){contactID = 3;}

            Statement statement = JDBC.connection.createStatement();
            JDBC.connection.createStatement().executeUpdate("INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) Value (\"" + title.getText() + "\", \"" + description.getText() + "\", \"" + location.getText() + "\", \"" + type.getValue().toString() + "\", \"" + startSTRING + "\", \"" + endSTRING + "\", \"" + dtf.format(LocalDateTime.now()).toString()  + "\", \"software\", \"" + dtf.format(LocalDateTime.now()).toString() +"\", \"software\",  " + customerID.getValue().toString() + ", " + userID.getValue().toString() + ", " + contactID.toString() +")");
            appointment.getAllAppointments().clear();
            contacts.clear();
            times.clear();
            times2.clear();
            types.clear();
            root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();

            //INSERT INTO appointments(Title, Description, Location, Type, Start, End, Create_Date, Created_By, Last_Update, Last_Updated_By, Customer_ID, User_ID, Contact_ID) Value ("big title", "description2", "biglocation", "good type", "2020-05-28 12:00:00", "2020-05-28 12:00:00", "2020-05-28 12:00:00", "script", "2020-05-28 12:00:00", "last updated by",  1, 2, 3)
        }
    }


    /**This function initializes the observablelists and comboboxes with appropriate information. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contacts.add("Anika Costa");
        contacts.add("Daniel Garcia");
        contacts.add("Li Lee");

        types.add("Planning Session");
        types.add("De-Briefing");

        userIDList.clear();
        userIDList.add(1);
        userIDList.add(2);

        type.setItems(types);
        userID.setItems(userIDList);
        customerID.setItems(customer.getAllCustomerID());
        

        times.add("08:00:00");
        times.add("09:00:00");
        times.add("10:00:00");
        times.add("11:00:00");
        times.add("12:00:00");
        times.add("13:00:00");
        times.add("14:00:00");
        times.add("15:00:00");
        times.add("16:00:00");
        times.add("17:00:00");
        times.add("18:00:00");
        times.add("19:00:00");
        times.add("20:00:00");
        times.add("21:00:00");
        times.add("22:00:00");



        times.forEach((a) ->{
            ZoneId localZoneId = ZoneId.of(TimeZone.getDefault().getID());
            LocalDate date = LocalDate.of(2022, 1, 1);
            LocalTime time = LocalTime.of(Integer.valueOf(a.toString().substring(0, 2)), 00,00);
            ZonedDateTime endZDT = ZonedDateTime.of(date, time, ZoneId.of("America/New_York"));
            Instant endInstant = endZDT.toInstant();
            ZonedDateTime endUTCtoLocal = endInstant.atZone(localZoneId);
            String dateTime = String.valueOf(endUTCtoLocal.toLocalTime() + ":00");


            System.out.println(dateTime);
            times2.add(dateTime);
        });



        contact.setItems(contacts);
        start.setItems(times2);
        end.setItems(times2);
    }
}