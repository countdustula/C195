package com.example.c195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**This class represents the appointments that the user can create. */
public class appointment {
    /**This represents the appointment ID. */
    private Integer id;
    /**This represents the title of the appointment. */
    private String title;
    /**This represents the description of the appointment. */
    private String description;
    /**This is the location of the appointment. */
    private String location;
    /**This is the type of appointment. */
    private String type;
    /**This is the start of the appointment. */
    private String start;
    /**This is the end of the appointment. */
    private String end;
    /**This is the date that the appointment was created. */
    private String createDate;
    /**This represents what the appointment entry was created by. */
    private String createdBy;
    /**This represents the last time the appointment was updated. */
    private String lastUpdate;
    /**This represents how the last update was conducted. */
    private String lastUpdatedBy;
    /**This is the customer ID that is associated with the appointment. */
    private Integer customerID;
    /**This is the user ID that is associated with the appointment. */
    private Integer userID;
    /**This is the contact that is associated with that appointment. */
    private Integer contact;
    /**This is the ObservableList of all the appointments available. */
    public static ObservableList<Object> allAppointments = FXCollections.observableArrayList();
    /**This is the ArrayList of all the appointments available.  This is used so that ArrayList operations can take place alongside the ObservableList. */
    public static ArrayList<appointment> allAppointmentsArrayList = new ArrayList<>();

    /**This is the appointment constructor. */
    public appointment(Integer id, String title, String description, String location, String type, String start, String end, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, Integer customerID, Integer userID, Integer contactID) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.location = location;
        this.type = type;
        this.start = start;
        this.end = end;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.customerID = customerID;
        this.userID = userID;
        this.contact = contactID;
    }

    /**This is the getter for all of appointments in an ObservableList. */
    public static ObservableList<Object> getAllAppointments(){
        return allAppointments;
    }


    /**This function puts all of the database information into both the ArrayList and ObservableList. */
    public static void DBtoAL() {

        try {
            Statement statement = JDBC.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM appointments");
            while(result.next()){
                appointment appointment = new appointment(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8),
                        result.getString(9),
                        result.getString(10),
                        result.getString(11),
                        result.getInt(12),
                        result.getInt(13),
                        result.getInt(14));

                allAppointmentsArrayList.add(appointment);
                getAllAppointments().add(appointment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**This is the getter for the appointment ID. */
    public Integer getId() {
        return id;
    }

    /**This is the getter for the title of the appointment. */
    public String getTitle() {
        return title;
    }

    /**This is the getter for the description of the appointment. */
    public String getDescription() {
        return description;
    }


    /**This is the getter for the location of the appointment. */
    public String getLocation() {
        return location;
    }

    /**This is the getter for the contact of the appointment. */
    public Integer getContact() {
        return contact;
    }

    /**This is the getter for the type of appointment. */
    public String getType() {
        return type;
    }

    /**This is the getter for the start of the appointment. */
    public String getStart() {
        return start;
    }


    /**This is the getter for the end of the appointment. */
    public String getEnd() {
        return end;
    }


    /**This is the getter for the customer ID associated with the appointment. */
    public Integer getCustomerID() {
        return customerID;
    }


    /**This is the getter for the user ID associated with the appointment. */
    public Integer getUserID() {
        return userID;
    }


    /**This is the setter for the ID associated with the appointment. */
    public void setId(Integer id) {
        this.id = id;
    }


}
