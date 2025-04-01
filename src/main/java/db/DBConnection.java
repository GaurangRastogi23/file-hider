package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.SQLOutput;

public class DBConnection {
    public static Connection connection;
    public static Connection getConnection(){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/file-hider", "root", "Letsrock23@");
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("connection done");
        return  connection;
    }
    public static  void closeConnection(){
        if(connection != null){
            try {
                connection.close();
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
    }


}
