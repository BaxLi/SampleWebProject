package com.test.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.test.beans.Product;
import com.test.beans.User;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ApplicationDao {

    public List<Product> searchProducts(String searchString) {
        Product product = null;
        List<Product> products = new ArrayList<>();
//            try {
//                Class.forName("com.mysql.jdbc.Driver");
//            } catch (ClassNotFoundException ex) {
//                System.out.println("Class NOT FOUD !!!! ");
//                Logger.getLogger(ApplicationDao.class.getName()).log(Level.SEVERE, null, ex);
//            }             

        try {
//                        String url = "jdbc:mysql://127.0.0.1:3306/hplus?useSSL=false";
//                        String username = "projectUser";
//                        String password = "Qwert12345!";
//                        Connection connection = DriverManager.getConnection(url,username,password);
            Connection connection = DBConnection.getConnectionToDatabase();
            String sql = "select * from products where product_name like '%" + searchString + "%'";
            Statement statement = null;
            if (connection != null) {
                statement = connection.createStatement();
                ResultSet set = statement.executeQuery(sql);
                while (set.next()) {
                    product = new Product();
                    product.setProductId(set.getInt("product_id"));
                    product.setProductImgPath(set.getString("image_path"));
                    product.setProductName(set.getString("product_name"));
                    products.add(product);
                }
            }
        } catch (SQLException sQLException) {
            sQLException.printStackTrace();
        }
        return products;
    }

    public int registerUser(User user) {
        int rowsAffected = 0;
        try {
            // get the connection for the database
            Connection connection = DBConnection.getConnectionToDatabase();

            // write the insert query
            String insertQuery = "insert into users values(?,?,?,?,?,?)";

            // set parameters with PreparedStatement
            PreparedStatement statement = connection.prepareStatement(insertQuery);
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFirstName());
            statement.setString(4, user.getLastName());
            statement.setInt(5, user.getAge());
            statement.setString(6, user.getActivity());

            // execute the statement
            rowsAffected = statement.executeUpdate();
//            connection.close();

        } catch (SQLException exception) {
            exception.printStackTrace();
        } 
        return rowsAffected;
    }
}
