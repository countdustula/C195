package com.example.c195;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.*;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Locale;
import java.util.ResourceBundle;

public class loginController implements Initializable {

//    File file = new File("login_activity.txt");
//    FileWriter fileWriter = new FileWriter(file);
    PrintWriter pw = new PrintWriter(new FileOutputStream(
            new File("login_activity.txt"),
            true /* append = true */));



    /**
     * This is the stage for the main form
     */
    private Stage stage;

    /**
     * This is the scene for the main form
     */
    private Scene scene;

    /**
     * This is the parent for the main form
     */
    private Parent root;

    @FXML
    private TextField userId;
    @FXML
    private TextField userPassword;
    @FXML
    private Text locationText;
    @FXML
    private Button loginButton;
    @FXML
    private Label userIdLabel;
    @FXML
    private Label passwordLabel;
    @FXML
    private Label locationLabel;

    public loginController() throws IOException {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();
        locationText.setText(String.valueOf(zoneId));



        if ((String.valueOf(Locale.getDefault())).contentEquals("fr")) {
            userIdLabel.setText("Numéro d'utilisateur");
            passwordLabel.setText("Le mot de passe");
            loginButton.setText("Connexion");
            locationLabel.setText("emplacement");
        }

    }

    public static boolean isInt(String number) {
        try {
            int test = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static void showAlert(String windowText, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(windowText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    @FXML
    public void switchToMainScreen(ActionEvent actionEvent) throws IOException {

        if (!userId.getText().isEmpty() && isInt(userId.getText()) && !userPassword.getText().isEmpty()) {

            pw.println("Successful login by UserID: " + userId.getText() + " at " + LocalDateTime.now() + "\n");
            pw.close();

            root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } else {
            pw.println("Unsuccessful login by UserID: " + userId.getText() + " at " + LocalDateTime.now());
            pw.close();
            if (!String.valueOf(Locale.getDefault()).contentEquals("fr")) {
                showAlert("Invalid input.", "User ID must only contain numbers.", "Also make sure that the password isn't blank.");
            }
            else{
                showAlert("Entrée invalide.", "L'ID utilisateur ne doit contenir que des chiffres.", "Assurez-vous également que le mot de passe n'est pas vide.");
            }
        }

    }
}