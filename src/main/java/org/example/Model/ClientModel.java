package org.example.Model;

import org.example.Db.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ClientModel {

    public static String checkUser(String userName) throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        String sql = "SELECT Password FROM User WHERE User_Name = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, userName);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return resultSet.getString(1);
        }
        System.out.println("Error");
        return null;
    }
}
