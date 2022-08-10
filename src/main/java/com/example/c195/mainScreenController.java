package com.example.c195;

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
import java.util.ResourceBundle;

public class mainScreenController implements Initializable {


    private Stage stage;


    private Scene scene;


    private Parent root;

    @FXML
    TableView<Object> customers;
    @FXML
    TableView<Object> appointments;
    @FXML
    TableColumn<appointment, Integer> appointmentID;
    @FXML
    TableColumn<appointment, String> appointmentTitle;
    @FXML
    TableColumn<appointment, String> appointmentDescription;
    @FXML
    TableColumn<appointment, String> appointmentLocation;
    @FXML
    TableColumn<appointment, Integer> appointmentContact;
    @FXML
    TableColumn<appointment, String> appointmentType;
    @FXML
    TableColumn<appointment, String> appointmentStart;
    @FXML
    TableColumn<appointment, String> appointmentEnd;
    @FXML
    TableColumn<appointment, Integer> appointmentCustomerID;
    @FXML
    TableColumn<appointment, Integer> appointmentUserID;
    @FXML
    TableColumn<customer, Integer> customerID;
    @FXML
    TableColumn<customer, String> customerName;
    @FXML
    TableColumn<customer, String> customerAddress;
    @FXML
    TableColumn<customer, String> customerPostalCode;
    @FXML
    TableColumn<customer, String> customerPhoneNumber;
    @FXML
    TableColumn<customer, Integer> customerDivisionID;
    @FXML
    Button addCustomer;
    @FXML
    Button updateCustomer;
    @FXML
    Button deleteCustomer;

    @FXML
    public void switchToAddCustomer(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-customer.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

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

                customer.getAllCustomers().clear();
                customer.DBtoAL();
            }
        }
    }

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerID.setCellValueFactory(new PropertyValueFactory<customer, Integer>("id"));
        customerName.setCellValueFactory(new PropertyValueFactory<customer, String>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<customer, String>("address"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<customer, String>("postalCode"));
        customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<customer, String>("phone"));
        customerDivisionID.setCellValueFactory(new PropertyValueFactory<customer, Integer>("divisionID"));

        JDBC.openConnection();
        customer.DBtoAL();
        appointment.DBtoAL();


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




    }
}
