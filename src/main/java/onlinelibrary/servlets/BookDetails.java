package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;
import onlinelibrary.daoimpl.FavoritesImpl;
import onlinelibrary.models.Book;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/BookDetails")
public class BookDetails extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int showDetailsId = Integer.valueOf(request.getParameter("showDetailsId"));
        String userId = String.valueOf(request.getSession().getAttribute("username"));
        BookImpl bookImpl = new BookImpl();
        FavoritesImpl favoritesImpl = new FavoritesImpl();
        boolean isFavorite = false;
        try {
            isFavorite = favoritesImpl.isBookAreFavorite(showDetailsId, userId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        ArrayList<Book> bookList = bookImpl.getBookById(showDetailsId);
        request.setAttribute("bookDetailsList", bookList);
        request.setAttribute("isFavorite", isFavorite);
        System.out.println(isFavorite);
        request.getRequestDispatcher("/resources/jsp/bookdetails.jsp").forward(request, response);

    }
}