package org.example.controllers;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.model.ComplaintModel;
import org.example.model.dao.ComplaintDAO;
import org.example.model.dao.UserDAO;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@WebServlet("/complaint")
public class ComplaintController extends HttpServlet {

    private final ComplaintModel complaintModel = new ComplaintModel();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        ServletContext sc = req.getServletContext();
        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");

        if ("delete".equals(action)) {
            String complaintId = req.getParameter("complaintId");
            if (complaintId != null && !complaintId.isEmpty()) {
                boolean deleted = complaintModel.deleteComplaint(complaintId, ds);
                if (deleted) {
                    resp.sendRedirect(req.getContextPath() + "/complaint?status=deleted#myComplaints");
                } else {
                    resp.sendRedirect(req.getContextPath() + "/complaint?status=deleteError#myComplaints");
//                    doGet(req, resp);
                }
            } else {
                resp.sendRedirect(req.getContextPath() + "/complaint?status=deleteError#myComplaints");
//                doGet(req, resp);
            }
            return;
        }


        String description = req.getParameter("description");
        String date = req.getParameter("date");
        String uId = req.getParameter("userId");

        String id = UUID.randomUUID().toString();



        String complaintId = req.getParameter("complaintId");
        if (complaintId != null && !complaintId.isEmpty()) {
            ComplaintDAO complaintDAO = new ComplaintDAO(
                    complaintId,
                    description,
                    date,
                    "Pending",
                    "no",
                    uId
            );
            boolean execute = complaintModel.updateComplaint(complaintDAO,ds);
            if (execute) {
                resp.sendRedirect(req.getContextPath() + "/complaint?status=success#submitComplaints");
            } else {
                resp.sendRedirect(req.getContextPath() + "/complaint?status=error#submitComplaints");
            }//update

        } else {//save
            ComplaintDAO complaintDAO = new ComplaintDAO(
                    date,
                    description,
                    id,
                    "no",
                    uId
            );
            boolean execute = complaintModel.saveComplaint(complaintDAO,ds);
            if (execute) {
                resp.sendRedirect(req.getContextPath() + "/complaint?status=success#submitComplaints");
            } else {
                resp.sendRedirect(req.getContextPath() + "/complaint?status=error#submitComplaints");
            }

        }


    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = (String) req.getSession().getAttribute("id");

        if (userId == null) {
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
            return;
        }

        ServletContext sc = req.getServletContext();
        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");

        List<ComplaintDAO> complaints = complaintModel.getEmployComplaint(userId, ds);

        req.setAttribute("complaints", complaints);
        req.getRequestDispatcher("/html/dashboard.jsp").forward(req, resp);
    }
}
