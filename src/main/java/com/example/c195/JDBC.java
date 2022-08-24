package com.example.c195;

import java.sql.Connection;
import java.sql.DriverManager;

/**This is the class that helps the program connect to the database. */
public abstract class JDBC {
    /**This is the string that represents the protocol. */
    private static final String protocol = "jdbc";
    /**This is the string that represents the vendor. */
    private static final String vendor = ":mysql:";
    /**This is the string that represents the location. */
    private static final String location = "//localhost/";
    /**This is the string that represents the database name. */
    private static final String databaseName = "client_schedule";
    /**This is the string that represents the url for the database. */
    private static final String jdbcUrl = protocol + vendor + location + databaseName + "?connectionTimeZone = SERVER"; // LOCAL
    /**This is the string that references the driver. */
    private static final String driver = "com.mysql.cj.jdbc.Driver"; // Driver reference
    /**This is the String that represents the username. */
    private static final String userName = "sqlUser"; // Username
    /**This is the String that represents the password. */
    private static String password = "Passw0rd!"; // Password
    /**This represents the connection interface. */
    public static Connection connection;  // Connection Interface


    /**This opens the connection to the database. */
    public static void openConnection()
    {
        try {
            Class.forName(driver); // Locate Driver
            connection = DriverManager.getConnection(jdbcUrl, userName, password); // Reference Connection object
            System.out.println("Connection successful!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }

    /**This closes the connection to the database. */
    public static void closeConnection() {
        try {
            connection.close();
            System.out.println("Connection closed!");
        }
        catch(Exception e)
        {
            System.out.println("Error:" + e.getMessage());
        }
    }




}
