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
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class updateCustomerController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public static ObservableList<String> USID = FXCollections.observableArrayList();
    public static ObservableList<String> canadaID = FXCollections.observableArrayList();
    public static ObservableList<String> UKID = FXCollections.observableArrayList();
    Integer customerID;
    @FXML
    Button submit;
    @FXML
    Button cancel;
    @FXML
    TextField customerName;
    @FXML
    TextField customerAddress;
    @FXML
    TextField customerPostalCode;
    @FXML
    TextField customerPhone;
    @FXML
    TextField customerIDTextField;
    @FXML
    ComboBox<String> customerDivisionID = new ComboBox<>();
    public static ObservableList<String> countries = FXCollections.observableArrayList(
            "United Kingdom",
            "United States",
            "Canada"
    );
    @FXML
    ComboBox<String> customerCountry = new ComboBox(countries);

    @FXML
    public void cancelToMainForm(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }


    @FXML
    public void submit(ActionEvent actionEvent) throws IOException, SQLException {
        if(customerName.getText().isEmpty() ||
                customerAddress.getText().isEmpty() ||
                customerPostalCode.getText().isEmpty() ||
                customerPhone.getText().isEmpty() ||
                customerCountry.getValue() == null ||
                customerDivisionID.getValue() == null){
            loginController.showAlert("One or more fields is empty.",
                    "A field is empty.",
                    "Fill out all fields and try again.");
        }
        else{
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            Statement statement = JDBC.connection.createStatement();
            JDBC.connection.createStatement().executeUpdate("UPDATE customers\n" +
                    "SET\n" +
                    "\tCustomer_Name = '" + customerName.getText().toString() + "', \n" +
                    "\tAddress = '" + customerAddress.getText().toString() + "', \n" +
                    "\tPostal_Code = '" + customerPostalCode.getText().toString() + "', \n" +
                    "\tPhone = '" + customerPhone.getText().toString() + "', \n" +
                    "\tLast_Update = '" + dtf.format(LocalDateTime.now()).toString() + "', \n" +
                    "\tDivision_ID = '" + divisionID.divisionIDMap.get(customerDivisionID.getValue().toString())+ "' \n" +
                    "WHERE\n" +
                    "\tCustomer_ID = " + customerID);
            customer.getAllCustomers().clear();
            root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    private void comboAction(ActionEvent event) {
        if (customerCountry.getValue().contentEquals("United Kingdom")) {
            customerDivisionID.setItems(UKID);
        }
        if (customerCountry.getValue().contentEquals("United States")) {
            customerDivisionID.setItems(USID);
        }
        if (customerCountry.getValue().contentEquals("Canada")) {
            customerDivisionID.setItems(canadaID);
        }
    }

    public void setAllTextFields(customer customer) {
        customerName.setText(customer.getName());
        customerAddress.setText(customer.getAddress());
        customerPostalCode.setText(customer.getPostalCode());
        customerPhone.setText(customer.getPhone());
        customerDivisionID.setValue(divisionID.divisionIDMapIDtoName.get(customer.getDivisionID()));
        customerID = customer.getId();
        customerIDTextField.setText(String.valueOf(customer.getId()));

        if(customer.getId() < 55){
            customerCountry.setValue("United States");
        }
        if(customer.getId() < 73 && customer.getDivisionID() > 54){
            customerCountry.setValue("Canada");
        }
        if(customer.getId() > 72){
            customerCountry.setValue("United Kingdom");
        }

        if (customerCountry.getValue().contentEquals("United Kingdom")) {
            customerDivisionID.setItems(UKID);
        }
        if (customerCountry.getValue().contentEquals("United States")) {
            customerDivisionID.setItems(USID);
        }
        if (customerCountry.getValue().contentEquals("Canada")) {
            customerDivisionID.setItems(canadaID);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountry.setItems(countries);

        for (int i=0; i<divisionID.usDivId.size(); i++){
            USID.add(divisionID.usDivId.get(i).getDivisionName());
        }
        for (int i=0; i<divisionID.ukDivId.size(); i++){
            UKID.add(divisionID.ukDivId.get(i).getDivisionName());
        }
        for (int i=0; i<divisionID.canadaDivId.size(); i++){
            canadaID.add(divisionID.canadaDivId.get(i).getDivisionName());
        }


    }
}
