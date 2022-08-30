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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.ResourceBundle;

/**This is the main screen controller. */
public class mainScreenController implements Initializable {
    /**This is the stage for the main screen. */
    private Stage stage;
    /**This is the scene for the main screen. */
    private Scene scene;
    /**This is the root for the main screen. */
    private Parent root;
    /**This is the DateTimeFormatter for all of the dates in the program. */
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    /**This is the counter.  Once it's increased, there will be notification about upcoming appointments when reaching the main screen again. */
    public static Integer counter = 0;
    /**This is the upcoming appointment that will show in the alert. */
    public appointment upcomingAppointment;
    /**This is the Observable list that sorts the appointment by the current month. */
    public static ObservableList<Object> byMonthList = FXCollections.observableArrayList();
    /**This is the ObservableList that sorts the appointments by the current week. */
    public static ObservableList<Object> byWeekList = FXCollections.observableArrayList();
    /**This is the button that takes you to the reports. */
    @FXML
    Button reports;
    /**This is the tableview that contains the customers. */
    @FXML
    TableView<Object> customers;
    /**This is the tableview that contains the appointments. */
    @FXML
    TableView<Object> appointments;
    /**This is the table column for the appointment ID. */
    @FXML
    TableColumn<appointment, Integer> appointmentID;
    /**This is the table column for the appointment title. */
    @FXML
    TableColumn<appointment, String> appointmentTitle;
    /**This is the table column for the appointment description. */
    @FXML
    TableColumn<appointment, String> appointmentDescription;
    /**This is the table column for the appointment location. */
    @FXML
    TableColumn<appointment, String> appointmentLocation;
    /**This is the table column for the appointment contact. */
    @FXML
    TableColumn<appointment, Integer> appointmentContact;
    /**This is the table column for the appointment type. */
    @FXML
    TableColumn<appointment, String> appointmentType;
    /**This is the table column for the appointment start date. */
    @FXML
    TableColumn<appointment, String> appointmentStart;
    /**This is the table column for the appointment end date. */
    @FXML
    TableColumn<appointment, String> appointmentEnd;
    /**This is the table column for the appointment customer ID */
    @FXML
    TableColumn<appointment, Integer> appointmentCustomerID;
    /**This is the table column for the appointment user ID. */
    @FXML
    TableColumn<appointment, Integer> appointmentUserID;
    /**This is the table column for the customer ID. */
    @FXML
    TableColumn<customer, Integer> customerID;
    /**This is the table column for the customer name. */
    @FXML
    TableColumn<customer, String> customerName;
    /**This is the table column for the customer address. */
    @FXML
    TableColumn<customer, String> customerAddress;
    /**This is the table column for the customer postal address. */
    @FXML
    TableColumn<customer, String> customerPostalCode;
    /**This is the table column for the customer phone number. */
    @FXML
    TableColumn<customer, String> customerPhoneNumber;
    /**This is the table column for the customer division ID. */
    @FXML
    TableColumn<customer, Integer> customerDivisionID;
    /**This is the button used for adding a customer. */
    @FXML
    Button addCustomer;
    /**This is the button used to update a customer. */
    @FXML
    Button updateCustomer;
    /**This is the button used to delete a customer. */
    @FXML
    Button deleteCustomer;
    /**This is the button used to add an appointment. */
    @FXML
    Button addAppointment;
    /**This is the update appointment button. */
    @FXML
    Button updateAppointment;
    /**This is the button to delete an appointment. */
    @FXML
    Button deleteAppointment;
    /**These are the radio buttons to decide how to view the appointments. */
    @FXML
    RadioButton viewAll, byMonth, byWeek;

    /**This switches the tableview to only show appointments for the current month. */
    @FXML
    public void updateByMonth(){
        byMonthList.clear();
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++) {
            if (appointment.allAppointmentsArrayList.get(i).getStart().substring(5, 7).contentEquals(LocalDateTime.now().toString().substring(5, 7))){
                byMonthList.add(appointment.allAppointmentsArrayList.get(i));
            }
            else {
                System.out.println("ONE WAS BAD");
            }
        }

        appointments.setItems(byMonthList);
        System.out.println("YOU SELECTED UPDATE BY MONTH");
    }


    /**This switches the tablview to onlyn show appointments for the current week. */
    @FXML
    public void updateByWeek(){
        byWeekList.clear();
        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++) {
            if (Integer.valueOf(appointment.allAppointmentsArrayList.get(i).getStart().substring(8, 10)) - Integer.valueOf(LocalDateTime.now().toString().substring(8, 10)) <= 7
                    && Integer.valueOf(appointment.allAppointmentsArrayList.get(i).getStart().substring(8, 10)) - Integer.valueOf(LocalDateTime.now().toString().substring(8, 10)) >= 0
                    && appointment.allAppointmentsArrayList.get(i).getStart().substring(5, 7).contentEquals(LocalDateTime.now().toString().substring(5, 7)) ){
                byWeekList.add(appointment.allAppointmentsArrayList.get(i));
            }
            else {
                System.out.println("ONE WAS BAD");
            }
        }

        appointments.setItems(byWeekList);
        System.out.println("YOU SELECTED UPDATE BY WEEK");
    }


    /**This switches the tableview to show all appointments. */
    @FXML
    public void viewAllAppointments() {
        appointments.setItems(appointment.getAllAppointments());
        System.out.println("You selected view all appointments");
    }

    /**This function switches the screen to the add customer screen. */
    @FXML
    public void switchToAddCustomer(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-customer.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This function switches to the add appiontment screen. */
    @FXML
    public void switchToAddAppointment(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-appointment.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This function switches to the update appointment screen. */
    @FXML
    public void switchToUpdateAppointment(ActionEvent actionEvent) throws IOException, SQLException {
        if(!appointments.getSelectionModel().isEmpty()) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("update-appointment.fxml"));
            root = loader.load();

            appointment selectedAppointment = (appointment) appointments.getSelectionModel().getSelectedItem();

            updateAppointmentController updateAppointmentController = loader.getController();
            updateAppointmentController.setAllTextFields(selectedAppointment);


            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else{
            loginController.showAlert("No appointment selected", "You have not selected an appointment.", "Please select an appointment and try again.");
        }
    }


    /**This function deletes a customer. */
    @FXML
    public void deleteCustomer(ActionEvent actionEvent) throws SQLException {
        if (customers.getSelectionModel().isEmpty()) {
            loginController.showAlert("No customer selected", "You did not select a customer to delete."
                    , "Please select a customer and try again.");
        } else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting Customer", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Deleting Customer");
            alert.setHeaderText("You are about to delete this selected customer.");
            alert.setContentText("Are you sure you want to delete this customer?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {
                customer selectedCustomer = (customer) customers.getSelectionModel().getSelectedItem();
                String id = String.valueOf(selectedCustomer.getId());

                Statement statement = JDBC.connection.createStatement();
                JDBC.connection.createStatement().executeUpdate("delete from customers where Customer_ID=" + id);
                JDBC.connection.createStatement().executeUpdate("delete from appointments where Customer_ID=" + id);

                appointment.allAppointmentsArrayList.clear();
                appointment.allAppointments.clear();
                appointment.DBtoAL();
                customer.getAllCustomers().clear();
                customer.DBtoAL();
            }
        }
    }

    /**This function deletes the selected appointment. */
    @FXML
    public void deleteAppointment(ActionEvent actionEvent) throws SQLException {
        if (appointments.getSelectionModel().isEmpty()) {
            loginController.showAlert("No appointment selected", "You did not select an appointment to delete."
                    , "Please select an appointment and try again.");
        } else {
            appointment selectedAppointment = (appointment) appointments.getSelectionModel().getSelectedItem();
            String id = String.valueOf(selectedAppointment.getId());
            String type = String.valueOf(selectedAppointment.getType());
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting Customer", ButtonType.YES, ButtonType.NO);
            alert.setTitle("Deleting Appointment");
            alert.setHeaderText("This appointment has an ID of " + id + " and appointment type of " + type);
            alert.setContentText("Are you sure you want to delete this appointment?");
            alert.showAndWait();

            if (alert.getResult() == ButtonType.YES) {

                Statement statement = JDBC.connection.createStatement();
                JDBC.connection.createStatement().executeUpdate("delete from appointments where Appointment_ID=" + id);

                appointment.getAllAppointments().clear();
                appointment.allAppointmentsArrayList.clear();
                byMonthList.clear();
                byWeekList.clear();
                viewAll.setSelected(true);
                appointment.DBtoAL();
                appointments.setItems(appointment.getAllAppointments());
                loginController.showAlert("Appointment has been deleted.",
                        "Appointment with ID " + id + "and appointment type of " + type + " has been deleted.",
                        "Please click okay to continue");

            }
        }
    }


    /**This switches the screen to the update customer screen. */
    @FXML
    public void switchToUpdateCustomer(ActionEvent actionEvent) throws IOException {
        if(!customers.getSelectionModel().isEmpty()) {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("update-customer.fxml"));
            root = loader.load();

            customer selectedCustomer = (customer) customers.getSelectionModel().getSelectedItem();

            updateCustomerController updateCustomerController = loader.getController();
            updateCustomerController.setAllTextFields(selectedCustomer);

            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else{
            loginController.showAlert("Please select a customer", "No customer was selected", "Please select a customer and try again");
        }
    }


    /**This switches the screen to the switch reports screen. */
    @FXML
    public void switchToReports(ActionEvent actionEvent) throws IOException {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("reports.fxml"));
            root = loader.load();
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
    }

    /**This switches the screen to the contact schedule screen. */
    @FXML
    public void switchToContactSchedule(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("contact-schedule.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This screen switches to the percentage calculator screen. */
    @FXML
    public void switchToPercentages(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("percentages.fxml"));
        root = loader.load();
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    /**This initializes the tablewview and observable lists. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        final ToggleGroup group = new ToggleGroup();




        viewAll.setToggleGroup(group);
        viewAll.setSelected(true);
        byMonth.setToggleGroup(group);
        byWeek.setToggleGroup(group);

        customerID.setCellValueFactory(new PropertyValueFactory<customer, Integer>("id"));
        customerName.setCellValueFactory(new PropertyValueFactory<customer, String>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<customer, String>("address"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<customer, String>("postalCode"));
        customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<customer, String>("phone"));
        customerDivisionID.setCellValueFactory(new PropertyValueFactory<customer, Integer>("divisionID"));



        customer.getAllCustomersArrayList().clear();
        customer.getAllCustomers().clear();
        appointment.getAllAppointments().clear();
        appointment.allAppointmentsArrayList.clear();
        JDBC.openConnection();
        customer.DBtoAL();
        appointment.DBtoAL();

        divisionID.databaseDivisionIDConvertor();


        appointmentID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("id"));
        appointmentTitle.setCellValueFactory(new PropertyValueFactory<appointment, String>("title"));
        appointmentDescription.setCellValueFactory(new PropertyValueFactory<appointment, String>("description"));
        appointmentLocation.setCellValueFactory(new PropertyValueFactory<appointment, String>("location"));
        appointmentContact.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("contact"));
        appointmentType.setCellValueFactory(new PropertyValueFactory<appointment, String>("type"));
        appointmentStart.setCellValueFactory(new PropertyValueFactory<appointment, String>("start"));
        appointmentEnd.setCellValueFactory(new PropertyValueFactory<appointment, String>("end"));
        appointmentCustomerID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("customerID"));
        appointmentUserID.setCellValueFactory(new PropertyValueFactory<appointment, Integer>("userID"));

        customers.setItems(customer.getAllCustomers());
        appointments.setItems(appointment.getAllAppointments());

        LocalDateTime now = LocalDateTime.now();

        if(isThereUpcomingAppointment() == true && counter == 0 && String.valueOf(Locale.getDefault()).substring(0, 2).contentEquals("en")) {
            loginController.showAlert("Upcoming Appointment",
                    "You have an appointment within 15 minutes from now.",
                    "The appointment ID is " + upcomingAppointment.getId() + " and the date and times is " + upcomingAppointment.getStart());
        }
        else if(isThereUpcomingAppointment() == true && counter == 0 && String.valueOf(Locale.getDefault()).substring(0, 2).contentEquals("fr")) {
            loginController.showAlert("Rendez-vous à venir",
                    "Vous avez un rendez-vous dans 15 minutes à partir de maintenant.",
                    "L'identifiant du rendez-vous est " + upcomingAppointment.getId() + " et la date et l'heure sont " + upcomingAppointment.getStart());
        }
        else if(counter == 0 && String.valueOf(Locale.getDefault()).substring(0, 2).contentEquals("en")){
            loginController.showAlert("No Upcoming Appointments",
                    "You have no appointments within 15 minutes from now.",
                    "You have no appointments within 15 minutes from now.");
        }
        else if(counter == 0 && String.valueOf(Locale.getDefault()).substring(0, 2).contentEquals("fr")){
            loginController.showAlert("Aucun rendez-vous à venir",
                    "Vous n'avez pas de rendez-vous dans les 15 minutes à venir.",
                    "Vous n'avez pas de rendez-vous dans les 15 minutes à venir.");
        }

        counter++;
    }

    /**This function checks if there is an upcoming appointment or not. */
    private boolean isThereUpcomingAppointment() {
        String now = dtf.format(LocalDateTime.now()).toString();
        String dateOfAppointment = null;

        for(int i=0; i<appointment.allAppointmentsArrayList.size(); i++){
            if(appointment.allAppointmentsArrayList.get(i).getStart().substring(0, 10).contentEquals(now.substring(0,10)) &&
                    Integer.valueOf(appointment.allAppointmentsArrayList.get(i).getStart().substring(11, 13)) == ((Integer.valueOf(now.substring(11, 13)))) + 1 &&
            (Integer.valueOf(now.substring(14, 16))) >= 45) {
                dateOfAppointment = "FOUND ONE";
                upcomingAppointment = appointment.allAppointmentsArrayList.get(i);
                System.out.println(dateOfAppointment);
            }
        }

        if(dateOfAppointment == null){
            return false;
        }
        else{
            return true;
        }
    }
}
