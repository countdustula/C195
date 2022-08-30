package com.example.c195;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.TimeZone;

/**This is main. */
public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Appointment Scheduler");
        stage.setScene(scene);
        stage.show();
    }

    /**This is the main function, with the commented code to set the language to french. */
    public static void main(String[] args) throws SQLException {
//        Locale.setDefault(new Locale("fr"));
        launch();

        LocalDate testDate = LocalDate.of(2022, 8, 30);
        LocalTime testTime = LocalTime.of(13, 00,00);
        ZonedDateTime test = ZonedDateTime.of(testDate, testTime, ZoneId.of("Europe/Paris"));
        System.out.println(test.withZoneSameInstant(ZoneId.of(TimeZone.getDefault().getID())));
        ZoneId.getAvailableZoneIds().stream().filter(c -> c.contains("UTC")).forEach(System.out::println);

    }
}