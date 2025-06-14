package org.example.controllers;

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

@WebServlet("/signIn")
public class SignInController extends HttpServlet {

    private final UserModel userModel = new UserModel();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        ServletContext sc = req.getServletContext();
        BasicDataSource ds = (BasicDataSource) sc.getAttribute("ds");

        UserDAO userDAO = userModel.checkUser(email,password,ds);

        if (userDAO != null){
            req.getSession().setAttribute("username", userDAO.getUserName());
            req.getSession().setAttribute("role", userDAO.getRole());
            req.getSession().setAttribute("id",userDAO.getId());

            if ("admin".equalsIgnoreCase(userDAO.getRole())) {
                resp.sendRedirect(req.getContextPath() + "/admin");
            } else {
                resp.sendRedirect(req.getContextPath() + "/complaint");
            }
        }else {
            req.setAttribute("loginError", "Invalid email or password");
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }
    }
}
