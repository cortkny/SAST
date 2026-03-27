package com.vulnapp;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoginServlet extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(LoginServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        // Log user input (Log4Shell trigger point)
        logger.error("Login attempt: " + user);

        try {
            Class.forName("org.h2.Driver");
            Connection conn = DriverManager.getConnection("jdbc:h2:~/test", "sa", "");

            // SQL Injection vulnerability
            String query = "SELECT * FROM users WHERE username='" + user + "' AND password='" + pass + "'";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            if (rs.next()) {
                out.println("<h1>Login Successful</h1>");
            } else {
                out.println("<h1>Login Failed</h1>");
            }

            conn.close();
        } catch (Exception e) {
            out.println("<pre>" + e.getMessage() + "</pre>");
        }
    }
}
