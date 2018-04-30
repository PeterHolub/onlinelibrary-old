package onlinelibrary.servlets;

import onlinelibrary.daoimpl.FavoritesImpl;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

//Servlet for add to favorites by username and book id in data base
@WebServlet("/AddToFavorites")
public class AddToFavorites extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        FavoritesImpl favorites = new FavoritesImpl();
        String userId = request.getParameter("username");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        favorites.addToFavorites(userId, bookId);

        request.getRequestDispatcher("/BookServlet").forward(request, response);
    }
}







