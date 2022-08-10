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

public class addCustomerController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    public static ObservableList<String> countries= FXCollections.observableArrayList(
            "United Kingdom",
            "United States",
            "Canada"
    );
    public static ObservableList<Integer> USID = FXCollections.observableArrayList();
    public static ObservableList<Integer> canadaID = FXCollections.observableArrayList();
    public static ObservableList<Integer> UKID = FXCollections.observableArrayList();
    @FXML
    ComboBox<String> customerCountry = new ComboBox(countries);
    @FXML
    ComboBox<Integer> customerDivisionID = new ComboBox<>();

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
            JDBC.connection.createStatement().executeUpdate("INSERT INTO `customers`" +
                    " (`Customer_Name`,`Address`,`Postal_Code`,`Phone`,`Create_Date`," +
                    " `Created_By`, `Last_Update` , `Last_Updated_By`, `Division_ID`)" +
                    " VALUES ('" + customerName.getText().toString() + "','" + customerAddress.getText().toString() + "'," +
                    "'" +customerPostalCode.getText().toString() + "', '" + customerPhone.getText().toString() + "'," +
                    " '" + dtf.format(LocalDateTime.now()).toString()  + "', 'software'," +
                    " '" + dtf.format(LocalDateTime.now()).toString() + "', 'software', '" + customerDivisionID.getValue().toString() + "')");
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
        if(customerCountry.getValue().contentEquals("United Kingdom")) {
            customerDivisionID.setItems(UKID);
        }
        if(customerCountry.getValue().contentEquals("United States")) {
            customerDivisionID.setItems(USID);
        }
        if(customerCountry.getValue().contentEquals("Canada")) {
            customerDivisionID.setItems(canadaID);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountry.setItems(countries);

        for (int i=1; i<=54; i++){
            USID.add(i);
        }
        for (int i=60; i<=72; i++){
            canadaID.add(i);
        }
        for (int i=101; i<=104; i++){
            UKID.add(i);
        }
    }
}
