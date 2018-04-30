package onlinelibrary.servlets;

import onlinelibrary.daoimpl.FavoritesImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;


@WebServlet("/DeleteFromFavorites")
public class DeleteFromFavorites extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        FavoritesImpl favoritesImpl = new FavoritesImpl();
        String userId = request.getParameter("username");
        int bookId = Integer.parseInt(request.getParameter("bookId"));

        favoritesImpl.deleteFromFavorites(userId, bookId);

        request.getRequestDispatcher("/BookServlet").forward(request, response);
    }
}



