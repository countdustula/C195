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
    @FXML
    ComboBox<String> customerCountry = new ComboBox(countries);

    @FXML
    public void cancelToMainForm(ActionEvent actionEvent) throws IOException {
        customer.getAllCustomers().clear();
        root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        customerCountry.setItems(countries);
    }
}
