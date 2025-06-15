package org.example.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.commons.dbcp2.BasicDataSource;
import org.example.model.UserModel;
import org.example.model.dao.UserDAO;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/signup")
public class SignUpController extends HttpServlet {

    private final UserModel userModel = new UserModel();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ObjectMapper mapper = new ObjectMapper();

        String id = UUID.randomUUID().toString();

        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        String role = req.getParameter("role");

        UserDAO userDAO = new UserDAO(
                id,
                username,
                email,
                password,
                role
        );

        ServletContext sc = req.getServletContext();
        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");
        //user sign in logic

        boolean execute = userModel.saveUser(userDAO,ds);
        if (execute) {
            req.setAttribute("registrationSuccess", true);
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        } else {
            req.setAttribute("registrationError", "Email or username already exists!");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }
}
