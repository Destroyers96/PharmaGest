package com.example.login;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseConnection {
    public Connection databaselink;

    public Connection getConnection(){
        String databaseName = "TestLogin";
        String databaseUser = "postgres";
        String databasePassword = "postgres2505";
        String url = "jdbc:postgresql://localhost:5432/" + databaseName;

        try{
            Class.forName("org.postgresql.Driver");
            databaselink = DriverManager.getConnection(url, databaseUser, databasePassword);
        } catch (Exception e){
            e.printStackTrace();
        }
        return databaselink;
    }
}