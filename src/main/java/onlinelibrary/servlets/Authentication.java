package onlinelibrary.servlets;

import onlinelibrary.daoimpl.*;
import onlinelibrary.models.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.*;


@WebServlet("/Authentication")
public class Authentication extends HttpServlet {

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        boolean result = false;
        UsersImpl usersImpl = new UsersImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            for (Users users : usersImpl.getUsersList()) {

                if (users.getUserId().equals(username) && users.getPassword().equals(password)) {
                    result = true;
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (result) {

            String userRole = null;
            try {
                userRole = usersImpl.getGroupId(username);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            HttpSession session = request.getSession();

            session.setAttribute("username", username);

            session.setAttribute("userRole", userRole);

            request.getRequestDispatcher("/resources/jsp/main.jsp").forward(request, response);
        } else {
            request.setAttribute("ErrorLogin", "Wrong username or password");
            request.getRequestDispatcher("/login.jsp").forward(request, response);

        }
    }
}
