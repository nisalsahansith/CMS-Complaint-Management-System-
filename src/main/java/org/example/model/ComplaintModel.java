package org.example.model;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.model.dao.ComplaintDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.System.out;

public class ComplaintModel {
    public boolean saveComplaint(ComplaintDAO complaintDAO, BasicDataSource ds) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (Connection connection = ds.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(
                    "INSERT INTO complaint (id,description,date,reply,user_id) VALUES (?,?,?,?,?)"
            );
            pstm.setString(1,complaintDAO.getId());
            pstm.setString(2,complaintDAO.getDescription());
            pstm.setString(3,complaintDAO.getDate());
            pstm.setString(4,complaintDAO.getReply());
            pstm.setString(5,complaintDAO.getUId());

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

    public List<ComplaintDAO> getEmployComplaint(String userId, BasicDataSource ds){
        List<ComplaintDAO> complaints = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM complaint WHERE user_id = ?")) {
            ps.setString(1, userId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                complaints.add(new ComplaintDAO(
                        rs.getString("id"),
                        rs.getString("description"),
                        rs.getString("date"),
                        rs.getString("status"),
                        rs.getString("reply"),
                        rs.getString("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaints;
    }

    public boolean updateComplaint(ComplaintDAO complaintDAO, BasicDataSource ds) throws IOException {
        ObjectMapper mapper = new ObjectMapper();

        try (Connection connection = ds.getConnection()){
            PreparedStatement pstm = connection.prepareStatement(
                    "UPDATE complaint SET description = ?, date = ?, status = ?, reply = ? WHERE id = ?"
            );
            pstm.setString(1, complaintDAO.getDescription());
            pstm.setString(2, complaintDAO.getDate());
            pstm.setString(3, complaintDAO.getStatus());
            pstm.setString(4, complaintDAO.getReply());
            pstm.setString(5, complaintDAO.getId());

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

    public boolean deleteComplaint(String complaintId, BasicDataSource ds) {
        String sql = "DELETE FROM complaint WHERE id = ?";
        try (Connection connection = ds.getConnection();
             PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, complaintId);
            int execute = pstmt.executeUpdate();
            return execute > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<ComplaintDAO> getAll(BasicDataSource ds){
        List<ComplaintDAO> complaints = new ArrayList<>();

        try (Connection conn = ds.getConnection();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM complaint")) {

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                complaints.add(new ComplaintDAO(
                        rs.getString("id"),
                        rs.getString("description"),
                        rs.getString("date"),
                        rs.getString("status"),
                        rs.getString("reply"),
                        rs.getString("user_id")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return complaints;
    }
}
