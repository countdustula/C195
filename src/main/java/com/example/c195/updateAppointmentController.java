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
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TimeZone;


public class updateAppointmentController implements Initializable {
    public static ObservableList<Object> times2 = FXCollections.observableArrayList();
    private Stage stage;
    private Scene scene;
    private Parent root;
    public Integer appointmentID;
    @FXML
    Button cancel;
    @FXML
    Button submit;
    @FXML
    TextField title;
    @FXML
    TextField description;
    @FXML
    TextField location;
    @FXML
    ComboBox<Object> contact;
    @FXML
    ComboBox<Object> type;
    @FXML
    DatePicker date;
    @FXML
    ComboBox start;
    @FXML
    ComboBox end;
    @FXML
    ComboBox customerID;
    @FXML
    ComboBox userID;
    public static ObservableList<Object> contacts = FXCollections.observableArrayList();
    public static ObservableList<Object> times = FXCollections.observableArrayList();
    public static ObservableList<Object> userIDList = FXCollections.observableArrayList();
    public static ObservableList<Object> types = FXCollections.observableArrayList();
    public static String selectedAppointmentDate;
    public static String selectedAppointmentStart;
    public static String selectedAppointmentEnd;

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

        System.out.println(usedTimes);

        int selectedStart = Integer.valueOf(selectedAppointmentStart.substring(0,2));
        int selectedEnd = Integer.valueOf(selectedAppointmentEnd.substring(0, 2));
        String selectedDate = selectedAppointmentDate;


        for(int h=selectedStart; h<=selectedEnd; h++){
            String example;
            if(h < 10){
                example = selectedDate + " " + "0" + h + ":00:00";
            }
            else{
                example = selectedDate + " " + h + ":00:00";
            }
            usedTimes.remove(example);
        }

        System.out.println(usedTimes);

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

    @FXML
    public void submit(ActionEvent actionEvent) throws IOException, SQLException {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        if(title.getText().isEmpty() || description.getText().isEmpty() || location.getText().isEmpty() ||
                contact.getValue() == null || type.getValue() == null || start.getValue() == null||
                end.getValue() == null|| customerID.getValue() == null || userID.getValue() == null || date.getValue() == null){
            loginController.showAlert("test", "test", "test");
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
            JDBC.connection.createStatement().executeUpdate("UPDATE appointments\n" +
                    "SET\n" +
                    "\tTitle = '" + title.getText().toString() + "', \n" +
                    "\tDescription = '" + description.getText().toString() + "', \n" +
                    "\tLocation = '" + location.getText().toString() + "', \n" +
                    "\tType = '" + type.getValue().toString() + "', \n" +
                    "\tStart = '" + startSTRING + "', \n" +
                    "\tEnd = '" + endSTRING + "', \n" +
                    "\tLast_Update = '" + dtf.format(LocalDateTime.now()).toString() + "', \n" +
                    "\tCustomer_ID = '" + customerID.getValue().toString() + "', \n" +
                    "\tUser_ID = '" + userID.getValue().toString() + "', \n" +
                    "\tContact_ID = '" + contactID.toString() + "' \n" +

                    "WHERE\n" +
                    "\tAppointment_ID = " + appointmentID.toString() + ";");



            appointment.getAllAppointments().clear();
            times.clear();
            times2.clear();
            contacts.clear();
            types.clear();
            root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    public void setAllTextFields(appointment appointment) throws SQLException {
        title.setText(appointment.getTitle());
        description.setText(appointment.getDescription());
        location.setText(appointment.getLocation());
        if(appointment.getContact().equals(1)){contact.setValue("Anika Costa");}
        if(appointment.getContact().equals(2)){contact.setValue("Daniel Garcia");}
        if(appointment.getContact().equals(3)){contact.setValue("Li Lee");}
        type.setValue(appointment.getType());
        customerID.setValue(appointment.getCustomerID());
        userID.setValue(appointment.getUserID());
        date.setValue(LocalDate.parse(appointment.getStart().substring(0,10)));
        start.setValue(appointment.getStart().substring(11, 16) + ":00");
        end.setValue(appointment.getEnd().substring(11, 16) + ":00");

        Statement statement = JDBC.connection.createStatement();
        ResultSet result = statement.executeQuery("SELECT * FROM appointments WHERE Appointment_ID = " + appointment.getId());

        appointmentID = appointment.getId();
        selectedAppointmentDate =  appointment.getUTCStart().substring(0,10);
        selectedAppointmentStart = appointment.getUTCStart().substring(11, 19);
        selectedAppointmentEnd = appointment.getUTCEnd().substring(11, 19);
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contacts.add("Anika Costa");
        contacts.add("Daniel Garcia");
        contacts.add("Li Lee");

        types.add("Full Groom");
        types.add("Trim");

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
