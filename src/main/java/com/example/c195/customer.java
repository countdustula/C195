package com.example.c195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class customer {
private Integer id;
private String name;
private String address;
private String postalCode;
private String phone;
private String createDate;
private String createdBy;
private String lastUpdate;
private String lastUpdatedBy;
private Integer divisionID;
public static ObservableList<Object> allCustomers = FXCollections.observableArrayList();

    public customer(Integer id, String name, String address, String postalCode, String phone, String createDate, String createdBy, String lastUpdate, String lastUpdatedBy, Integer divisionID) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.postalCode = postalCode;
        this.phone = phone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
    }

    public static ObservableList<Object> getAllCustomers(){
        return allCustomers;
    }

    public static void DBtoAL() {

        try {
            Statement statement = JDBC.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM customers");
            while(result.next()){
                customer customera = new customer(result.getInt(1),
                        result.getString(2),
                        result.getString(3),
                        result.getString(4),
                        result.getString(5),
                        result.getString(6),
                        result.getString(7),
                        result.getString(8),
                        result.getString(9),
                        result.getInt(10));

                getAllCustomers().add(customera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getPhone() {
        return phone;
    }

    public String getCreateDate() {
        return createDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public Integer getDivisionID() {
        return divisionID;
    }
}
