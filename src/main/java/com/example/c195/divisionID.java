package com.example.c195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class divisionID {
    Integer divisionID;
    String divisionName;
    Integer countryID;
    public static ObservableList<divisionID> usDivId = FXCollections.observableArrayList();
    public static ObservableList<divisionID> ukDivId = FXCollections.observableArrayList();
    public static ObservableList<divisionID> canadaDivId = FXCollections.observableArrayList();
    public static HashMap<String, Integer> divisionIDMap = new HashMap<>();
    public static HashMap<Integer, String> divisionIDMapIDtoName = new HashMap<>();


    public divisionID(Integer divisionID, String divisionName, Integer countryID) {
        this.divisionID = divisionID;
        this.divisionName = divisionName;
        this.countryID = countryID;
    }

    public Integer getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(Integer divisionID) {
        this.divisionID = divisionID;
    }

    public String getDivisionName() {
        return divisionName;
    }

    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }



    public static void databaseDivisionIDConvertor() {

        try {
            Statement statement = JDBC.connection.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM first_level_divisions");
            while(result.next()){
                divisionID divisionID = new divisionID(result.getInt(1),
                        result.getString(2),
                        result.getInt(7));

                if(divisionID.getCountryID().equals(1)){
                    usDivId.add(divisionID);
                }
                else if(divisionID.getCountryID().equals(2)){
                    ukDivId.add(divisionID);
                }
                else{
                    canadaDivId.add(divisionID);
                }
                divisionIDMap.put(divisionID.getDivisionName(), divisionID.getDivisionID());
                divisionIDMapIDtoName.put(divisionID.getDivisionID(), divisionID.getDivisionName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}
