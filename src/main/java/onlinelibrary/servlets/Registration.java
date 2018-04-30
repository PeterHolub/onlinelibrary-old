package onlinelibrary.servlets;

import onlinelibrary.daoimpl.*;
import onlinelibrary.models.Users;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

//Users registration servlet (checks if user exists, if not registration = available, if exist sends message that user exist)
@WebServlet("/Registration")
public class Registration extends HttpServlet {
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        UsersImpl usersImpl = new UsersImpl();
        boolean result = true;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String groupId = "user";

        for (Users users : usersImpl.getUsersList()) {

            if (users.getUserId().equals(username)) {
                result = false;
                break;
            }
        }
        if (result) {
            try {
                usersImpl.userRegistration(username, password, groupId);
            } catch (Exception e) {
                e.printStackTrace();
            }

            request.getRequestDispatcher("/resources/jsp/registermessage.jsp").forward(request, response);

        } else {
            request.setAttribute("RegisterError", "Username already exist");
            request.getRequestDispatcher("/register.jsp").forward(request, response);
            }
    }
}
