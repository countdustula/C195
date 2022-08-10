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

public class addAppointmentController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
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
    TextField type;
    @FXML
    TextField start;
    @FXML
    TextField end;
    @FXML
    TextField customerID;
    @FXML
    TextField userID;
    public static ObservableList<Object> contacts = FXCollections.observableArrayList();

    @FXML
    public void cancel(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void submit(ActionEvent actionEvent) throws IOException {
        root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
        stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contacts.add(1);
        contacts.add(2);
        contacts.add(3);

        contact.setItems(contacts);
    }
}



//        •  Appointment_ID
//
//        •  Title
//
//        •  Description
//
//        •  Location
//
//        •  Contact
//
//        •  Type
//
//        •  Start Date and Time
//
//        •  End Date and Time
//
//        •  Customer_ID
//
//        •  User_ID