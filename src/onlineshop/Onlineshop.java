/* 
Evidencia: Codificación de módulos del software según requerimientos del proyecto
Aprendiz: Josue David Fischer Yepes
Ficha: 2547406
*/

package onlineshop;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Onlineshop {
    public static void main(String[] args) {
        
        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/onlineshop";
        Connection connection;
        Statement statement;
        ResultSet rs;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Onlineshop.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection = DriverManager.getConnection(url, user, password);
            statement = connection.createStatement();

            String insertQuery = "INSERT INTO USERS(USERID, USERNAME, USEREMAIL, USERPASSWORD) VALUES(?, ?, ?, ?)";
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setInt(1, 1);
                insertStatement.setString(2, "josue");
                insertStatement.setString(3, "josue@email.com");
                insertStatement.setString(4, "123456");
                insertStatement.executeUpdate();
            }
            
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setInt(1, 2);
                insertStatement.setString(2, "juan");
                insertStatement.setString(3, "juan@email.com");
                insertStatement.setString(4, "789012");
                insertStatement.executeUpdate();
            }
            
            try (PreparedStatement insertStatement = connection.prepareStatement(insertQuery)) {
                insertStatement.setInt(1, 3);
                insertStatement.setString(2, "maria");
                insertStatement.setString(3, "maria@email.com");
                insertStatement.setString(4, "abcdef");
                insertStatement.executeUpdate();
            }

            String selectQuery = "SELECT * FROM USERS";
            rs = statement.executeQuery(selectQuery);
            while (rs.next()) {
                System.out.println(rs.getInt("userid") + ": " + rs.getString("username"));
            }

            String updateQuery = "UPDATE USERS SET USERPASSWORD = ? WHERE USERNAME = ?";
            try (PreparedStatement updateStatement = connection.prepareStatement(updateQuery)) {
                updateStatement.setString(1, "newpassword");
                updateStatement.setString(2, "josue");
                updateStatement.executeUpdate();
            }

            String deleteQuery = "DELETE FROM USERS WHERE USERNAME = ?";
            try (PreparedStatement deleteStatement = connection.prepareStatement(deleteQuery)) {
                deleteStatement.setString(1, "josue");
                deleteStatement.executeUpdate();
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Onlineshop.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}