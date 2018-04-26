package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;


@WebServlet("/BookDelete")

public class BookDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookName = request.getParameter("deleteId");
        BookImpl bookImpl = new BookImpl();
        try {
            bookImpl.deleteBook(bookName);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        request.getRequestDispatcher("/resources/jsp/books.jsp").forward(request, response);

    }

}

