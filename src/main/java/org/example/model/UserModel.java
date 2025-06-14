package org.example.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.model.dao.UserDAO;

import javax.sql.DataSource;
import java.awt.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.UUID;

import static java.lang.System.out;

public class UserModel {

    public boolean saveUser(UserDAO userDAO, BasicDataSource ds) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        try(Connection connection = ds.getConnection()) {
            PreparedStatement pstm = connection.prepareStatement(
                    "INSERT INTO user (id, username, email, password, role) " +
                            "VALUES (?, ?, ?, ?, ?)"
            );

//            String eid = UUID.randomUUID().toString();

            pstm.setString(1, userDAO.getId());
            pstm.setString(2, userDAO.getUserName());
            pstm.setString(3, userDAO.getEmail());
            pstm.setString(4, userDAO.getPassword());
            pstm.setString(5, userDAO.getRole());
            int execute = pstm.executeUpdate();

            connection.close();

            return execute > 0;
        } catch (SQLException e) {
            mapper.writeValue(out, Map.of(
                    "code","500",
                    "status","error",
                    "message","Internal server error!"
            ));
            throw new RuntimeException(e);
        }
    }

    public UserDAO checkUser(String email, String password, DataSource ds){
        try (Connection connection = ds.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(
                    "SELECT * FROM user WHERE email = ? AND password = ?");
            pstm.setString(1,email);
            pstm.setString(2,password);
            ResultSet list = pstm.executeQuery();
            if (list.next()) {
                return new UserDAO(list.getString(1),
                        list.getString(2),
                        list.getString(3),
                        list.getString(4),
                        list.getString(5));
            }else {
                return null;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
