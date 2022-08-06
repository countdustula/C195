package com.example.c195;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class mainScreenController implements Initializable {
    @FXML
    TableView<Object> customers;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerID.setCellValueFactory(new PropertyValueFactory<customer, Integer>("id"));
        customerName.setCellValueFactory(new PropertyValueFactory<customer, String>("name"));
        customerAddress.setCellValueFactory(new PropertyValueFactory<customer, String>("address"));
        customerPostalCode.setCellValueFactory(new PropertyValueFactory<customer, String>("postalCode"));
        customerPhoneNumber.setCellValueFactory(new PropertyValueFactory<customer, String>("phone"));

        JDBC.openConnection();
        customer.DBtoAL();



        customers.setItems(customer.getAllCustomers());

    }
}
