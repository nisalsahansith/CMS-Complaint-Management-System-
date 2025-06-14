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

import java.io.IOException;
import java.util.List;

@WebServlet("/admin")
public class AdminPageController extends HttpServlet {

    private final ComplaintModel complaintModel = new ComplaintModel();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext sc = req.getServletContext();
        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");

        List<ComplaintDAO> complaints = complaintModel.getAll(ds);

        req.setAttribute("complaints", complaints);
        req.getRequestDispatcher("/html/adminPage.jsp").forward(req, resp);
    }

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
                    resp.sendRedirect(req.getContextPath() + "/admin#myComplaints");
                } else {
                    req.setAttribute("deletionError", "Failed to delete complaint. Please try again.");
                    doGet(req, resp);
                }
            } else {
                req.setAttribute("deletionError", "Invalid complaint ID.");
                doGet(req, resp);
            }
            return;
        }

        String id = req.getParameter("complaintId");
        String description = req.getParameter("description");
        String date = req.getParameter("date");
        String uId = req.getParameter("uid");
        String status = req.getParameter("status");
        String reply = req.getParameter("reply");
        if (reply == null || reply.trim().isEmpty()) {
            reply = "no";
        }

        ComplaintDAO complaintDAO = new ComplaintDAO(
                id,
                description,
                date,
                status,
                reply,
                uId
        );

        boolean isUpdate = complaintModel.updateComplaint(complaintDAO,ds);
        if (isUpdate){
            resp.sendRedirect(req.getContextPath() + "/admin#myComplaints");
        }else {
            req.setAttribute("updateError", "Failed to update complaint. Please try again.");
            doGet(req, resp);
        }
    }
}
