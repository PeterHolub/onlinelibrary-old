package onlinelibrary.servlets;

import onlinelibrary.daoimpl.FavoritesImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/AddToFavorites")
public class AddToFavorites extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        FavoritesImpl favorites = new FavoritesImpl();
        String userId = request.getParameter("username");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        try {
            favorites.addToFavorites(userId, bookId);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        request.getRequestDispatcher("/resources/jsp/books.jsp").forward(request, response);
    }
}







