package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;
import java.io.IOException;
import java.sql.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/BookRead")
public class BookRead extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        BookImpl bookImpl = new BookImpl();

        int readId = Integer.valueOf(request.getParameter("readId"));
        response.setContentType("application/pdf");
        try {
            response.getOutputStream().write(bookImpl.getBook(readId));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
