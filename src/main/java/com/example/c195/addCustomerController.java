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
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

/**This class is the controller for the "Add Customer" scene. */
public class addCustomerController implements Initializable {
    /**This is the stage for the screen */
    private Stage stage;
    /**This is the scene for the screen. */
    private Scene scene;
    /**This is the root for the screen */
    private Parent root;
    /**This is the submit button, to submit a new customer. */
    @FXML
    Button submit;
    /**This is the cancel button, to cancel making a new customer. */
    @FXML
    Button cancel;
    /**This is the textfield where the user inputs the customer name. */
    @FXML
    TextField customerName;
    /**This is the textfield where the user inputs the customer address. */
    @FXML
    TextField customerAddress;
    /**This is the textfield where the user inputs the customer postal code. */
    @FXML
    TextField customerPostalCode;
    /**This is the textfield where the user inputs the customer phone number. */
    @FXML
    TextField customerPhone;
    @FXML
    TextField customerID;

    /**This is the Observablelist of available countries within the software. */
    public static ObservableList<String> countries= FXCollections.observableArrayList(
            "United Kingdom",
            "United States",
            "Canada"
    );
    /**This is the Observablelist of the US ID's */
    public static ObservableList<String> USID = FXCollections.observableArrayList();
    /**This is the Observablelist of Canada's ID's */
    public static ObservableList<String> canadaID = FXCollections.observableArrayList();
    /**This is the Observablelist of the UK's ID's */
    public static ObservableList<String> UKID = FXCollections.observableArrayList();
    /**This is the combobox where the user selects their country. */
    @FXML
    ComboBox<String> customerCountry = new ComboBox(countries);
    /**This is the combobox where the user selects the customer's division ID. */
    @FXML
    ComboBox<String> customerDivisionID = new ComboBox<>();


    /**This is the function associated with the cancel button, to go back a screen. */
    @FXML
    public void cancelToMainForm(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    /**This is an instance of the interface to use the alert lambda function. */
    MyInterface myInterface = (x, y, z) ->{
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Deleting Customer", ButtonType.YES, ButtonType.NO);
        alert.setTitle(x);
        alert.setHeaderText(y);
        alert.setContentText(z);
        alert.showAndWait();
    };

    /**This is the function associated with the submit button, to add a customer. */
    @FXML
    public void submit(ActionEvent actionEvent) throws IOException, SQLException {
        if(customerName.getText().isEmpty() ||
                customerAddress.getText().isEmpty() ||
                customerPostalCode.getText().isEmpty() ||
                customerPhone.getText().isEmpty() ||
                customerCountry.getValue() == null ||
                customerDivisionID.getValue() == null){
            myInterface.alert("One or more fields is empty.",
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
                    " '" + dtf.format(LocalDateTime.now()).toString() + "', 'software', '" + divisionID.divisionIDMap.get(customerDivisionID.getValue().toString()) + "')");
            customer.getAllCustomers().clear();
            root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }


    /**This is the function associated with setting the customer division ID based upon the customer's country. */
    @FXML
    private void comboAction(ActionEvent event) {
        if(customerCountry.getValue().toString().contentEquals("United Kingdom")) {
            customerDivisionID.setItems(UKID);
        }
        if(customerCountry.getValue().toString().contentEquals("United States")) {
            customerDivisionID.setItems(USID);
        }
        if(customerCountry.getValue().toString().contentEquals("Canada")) {
            customerDivisionID.setItems(canadaID);
        }
    }

    /**This initalizes the observablelists and comboboxes upon reaching the screen.  It also includes another example of a lambda expression. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountry.setItems(countries);
        customerID.setText("Autogenerated");

        for (int i=0; i<divisionID.usDivId.size(); i++){
            USID.add(divisionID.usDivId.get(i).getDivisionName());
        }
        for (int i=0; i<divisionID.ukDivId.size(); i++){
            UKID.add(divisionID.ukDivId.get(i).getDivisionName());
        }
//        for (int i=0; i<divisionID.canadaDivId.size(); i++){
//            canadaID.add(divisionID.canadaDivId.get(i).getDivisionName());
//        }
        /**This is another case of a lambda expression.  Instead of writing a "for" loop, I used this simpler expression. */
        divisionID.canadaDivId.forEach((a) ->{
           canadaID.add(a.getDivisionName());
        });
    }
}
