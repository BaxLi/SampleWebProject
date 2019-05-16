
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author User
 */
public class TestJDBCConnection {
    public static void main(String[] args) {
    
    String url = "jdbc:mysql://127.0.0.1:3306/hplus?useSSL=false";
    String username = "projectUser";
    String password = "Qwert12345!";

        System.out.println("Connecting database...");

try (Connection connection = DriverManager.getConnection(url, username, password)) {
    System.out.println("Database connected!");
} catch (SQLException e) {
    throw new IllegalStateException("Cannot connect the database!", e);
} finally {
    System.out.println("Finally");
}
    }
}

