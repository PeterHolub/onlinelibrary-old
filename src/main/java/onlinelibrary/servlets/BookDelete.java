package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
//Servlet for deleting book from database
@WebServlet("/BookDelete")

public class BookDelete extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String bookName = request.getParameter("deleteId");
        BookImpl bookImpl = new BookImpl();

        bookImpl.deleteBook(bookName);

        request.getRequestDispatcher("/BookServlet").forward(request, response);

    }

}

