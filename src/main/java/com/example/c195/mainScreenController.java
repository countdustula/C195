package com.example.c195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class mainScreenController implements Initializable {


    private Stage stage;


    private Scene scene;


    private Parent root;

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
    @FXML
    TableColumn<customer, Integer> customerDivisionID;
    @FXML
    Button addCustomer;

    @FXML
    public void switchToAddCustomer(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("add-customer.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
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



        customers.setItems(customer.getAllCustomers());

    }
}
