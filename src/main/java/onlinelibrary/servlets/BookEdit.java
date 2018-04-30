package onlinelibrary.servlets;

import onlinelibrary.daoimpl.*;
import onlinelibrary.models.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

//Servlet for loading data for bookedit.jsp
@WebServlet("/BookEdit")
public class BookEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int editId = Integer.valueOf(request.getParameter("editId"));
        BookImpl bookImpl = new BookImpl();
        ArrayList<Book> bookList = bookImpl.getBookById(editId);

        request.setAttribute("bookEditList", bookList);
        request.getRequestDispatcher("/resources/jsp/bookedit.jsp").forward(request, response);

    }
}




