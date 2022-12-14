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

/**This is the controller for the login screen. */
public class loginController implements Initializable {

    /**
     * This is the printwriter that is used to copy text to the "login_activity.txt"
     */
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

    /**This is the textfield for the user ID */
    @FXML
    private TextField userId;
    /**This is the textfield for the user's password */
    @FXML
    private TextField userPassword;
    /**This is the text that displays the location. */
    @FXML
    private Text locationText;
    /**This is the login button. */
    @FXML
    public Button loginButton;
    /**This is the label that signifies the user ID text box. */
    @FXML
    private Label userIdLabel;
    /**This is the label that signifies the password text box. */
    @FXML
    private Label passwordLabel;
    /**This is the label that signifies the location. */
    @FXML
    private Label locationLabel;

    /**This is included for the Printwriter to work properly. */
    public loginController() throws FileNotFoundException {
    }



    /**This initializes the location text and decides what language the login screen will be in. */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ZoneId zoneId = ZoneId.systemDefault();
        locationText.setText(String.valueOf(zoneId));


        if ((String.valueOf(Locale.getDefault())).substring(0,2).contentEquals("fr")) {
            userIdLabel.setText("Num??ro d'utilisateur");
            passwordLabel.setText("Le mot de passe");
            loginButton.setText("Connexion");
            locationLabel.setText("emplacement");
        }
    }

    /**This decides whether the input is an integer or not. */
    public static boolean isInt(String number) {
        try {
            int test = Integer.parseInt(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**This is the function that shows an alert when needed. */
    public static void showAlert(String windowText, String headerText, String contentText) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(windowText);
        alert.setHeaderText(headerText);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    public Integer setUserIdAndPassword(Integer int1, Integer int2){
        return int1 + int2;
    }

    public boolean doesExistInDB(String username, String password){
        if(username.contentEquals("test") && password.contentEquals("test")){
            return true;
        }
        else if((username.contentEquals("admin") && password.contentEquals("admin"))){
            return true;
        }
        else{
            return false;
        }
    }

    /**This function brings you to the main screen if the logic is correct for the sign in. */
    @FXML
    public boolean switchToMainScreen(ActionEvent actionEvent) throws IOException {

        if (doesExistInDB(userId.getText(), userPassword.getText())){

            pw.println("Successful login by UserID: " + userId.getText() + " at " + LocalDateTime.now() + "\n");
            pw.close();



            root = FXMLLoader.load(getClass().getResource("main-screen.fxml"));
            stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
            return true;
        } else {
            pw.println("Unsuccessful login by UserID: " + userId.getText() + " at " + LocalDateTime.now());
            pw.close();
            if (!String.valueOf(Locale.getDefault()).substring(0, 2).contentEquals("fr")) {
                showAlert("Invalid input.", "The login credentials were invalid.", "Please try again.");
            }
            else{
                showAlert("Entr??e invalide.", "Les identifiants de connexion ??taient invalides.", "Veuillez r??essayer.");
            }

            return false;
        }

    }
}