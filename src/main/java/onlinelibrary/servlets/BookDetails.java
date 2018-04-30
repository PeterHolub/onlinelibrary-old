package onlinelibrary.servlets;

import onlinelibrary.daoimpl.*;
import onlinelibrary.models.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
//Servlet for loading data for bookdetails.jsp
@WebServlet("/BookDetails")
public class BookDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int showDetailsId = Integer.valueOf(request.getParameter("showDetailsId"));
        String userId = String.valueOf(request.getSession().getAttribute("username"));
        BookImpl bookImpl = new BookImpl();
        FavoritesImpl favoritesImpl = new FavoritesImpl();
        boolean isFavorite = favoritesImpl.isBookAreFavorite(showDetailsId, userId);

        ArrayList<Book> bookList = bookImpl.getBookById(showDetailsId);

        request.setAttribute("bookDetailsList", bookList);
        request.setAttribute("isFavorite", isFavorite);
        request.getRequestDispatcher("/resources/jsp/bookdetails.jsp").forward(request, response);

    }
}