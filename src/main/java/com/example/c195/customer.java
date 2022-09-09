package com.example.c195;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**This is the class for the customer object. */
public class customer {
    /**This represents the ID for the customer. */
    private Integer id;
    /**This represents the name for the customer. */
    private String name;
    /**This represents the address for the customer. */
    private String address;
    /**This represents the postal code for the customer. */
    private String postalCode;
    /**This represents the phone number for the customer. */
    private String phone;
    /**This represents the date the customer was created. */
    private String createDate;
    /**This represents how the customer was created. */
    private String createdBy;
    /**This represents the last time this customer's information was updated. */
    private String lastUpdate;
    /**This represents how the customer was last updated. */
    private String lastUpdatedBy;
    /**This represents the customer's division ID. */
    private Integer divisionID;
    /**This is the ObservableList of all the customers that have been created. */
    public static ObservableList<customer> allCustomers = FXCollections.observableArrayList();
    /**This represents the ArrayList of all the customers that have been created. */
    public static ArrayList<customer> allCustomersArrayList = new ArrayList<>();

    /**This is the constructor for the a customer. */
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

    /**This returns the ObservableList of all the customers. */
    public static ObservableList<customer> getAllCustomers(){
        return allCustomers;
    }

    /**This puts all of the customers in the database into the ObservableList and ArrayList. */
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

                allCustomersArrayList.add(customera);
                getAllCustomers().add(customera);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
}

    /**This is the setter for the customer ID. */
    public void setId(Integer id) {
        this.id = id;
    }

    /**This is the getter for the customer ID. */
    public Integer getId() {
        return id;
    }

    /**This is the getter for the customer name. */
    public String getName() {
        return name;
    }

    /**This is the getter for the customer address. */
    public String getAddress() {
        return address;
    }

    /**This is the getter for the customer postal code. */
    public String getPostalCode() {
        return postalCode;
    }

    /**This is the getter for the customer phone numberl. */
    public String getPhone() {
        return phone;
    }

    /**This is the getter for the division ID. */
    public Integer getDivisionID() {
        return divisionID;
    }

    /**This is the getter for the customer arraylist. */
    public static ArrayList<customer> getAllCustomersArrayList(){
        return allCustomersArrayList;
    }

    /**This is the getter for getting all of the customer ID's */
    public static ObservableList<Object> getAllCustomerID() {
        ObservableList ans = FXCollections.observableArrayList();

        for(int i=0; i < allCustomersArrayList.size(); i++){
            ans.add(allCustomersArrayList.get(i).getId());
        }

        return ans;
    }

}
