package onlinelibrary.servlets;
import onlinelibrary.daoimpl.BookImpl;
import onlinelibrary.models.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/BookEdit")
public class BookEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BookImpl bookImpl = new BookImpl();
        int editId = Integer.valueOf(request.getParameter("editId"));

        ArrayList<Book> bookList = bookImpl.getBookById(editId);
        request.setAttribute("bookEditList", bookList);
        request.getRequestDispatcher("/resources/jsp/bookedit.jsp").forward(request, response);

    }
}




