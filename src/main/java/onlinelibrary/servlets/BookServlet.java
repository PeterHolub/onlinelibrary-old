package onlinelibrary.servlets;

import onlinelibrary.daoimpl.*;
import onlinelibrary.enums.SearchType;
import onlinelibrary.models.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

//Main Servlet that shows list of books by search criteria, and favorite option
@WebServlet("/BookServlet")

public class BookServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Book> list = null;

        String username = String.valueOf(request.getSession().getAttribute("username"));
        BookImpl bookImpl = new BookImpl();
        FavoritesImpl favoritesImpl = new FavoritesImpl();

        ArrayList<Favorites> favoritesList = favoritesImpl.getFavorites(username);

        request.getSession().setAttribute("selectedGenre", request.getParameter("genre_id"));

        if (request.getParameter("search_string") != null) {
            String searchStr = request.getParameter("search_string");
            SearchType type = SearchType.TITLE;
            if (request.getParameter("search_option").equals("Author")) {
                type = SearchType.AUTHOR;
            } else if (request.getParameter("search_option").equals("Genre")) {
                type = SearchType.GENRE;
            }


            if (searchStr != null && !searchStr.trim().equals("")) {

                list = bookImpl.getBooksBySearch(searchStr, type);

            }

        } else if (request.getParameter("userfavorites") != null) {
            String userFavorites = request.getParameter("userfavorites");

            list = bookImpl.getBooksByUserFavorites(userFavorites);

        } else {

            list = bookImpl.getAllBooks();
        }

        //Search option
        request.setCharacterEncoding("UTF-8");
        String searchString = "";

        if (request.getParameter("search_string") != null) {
            searchString = request.getParameter("search_string");
            request.getSession().setAttribute("search_string", searchString);


        } else {
            request.getSession().setAttribute("search_string", searchString);
        }

        //Create var for script to keeping selected option after refresh
        String dropList = request.getParameter("search_option");
        request.getSession().setAttribute("dropList", dropList);
        request.getSession().setAttribute("favorite", favoritesList);
        request.setAttribute("currentBookList", list);

        request.getRequestDispatcher("/resources/jsp/books.jsp").forward(request, response);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getSession().setAttribute("selectedGenre", request.getParameter("genre_id"));
        BookImpl bookImpl = new BookImpl();
        //variations to show books by different criteria
        if (request.getParameter("genre_id") != null) {
            int genreId = Integer.valueOf(request.getParameter("genre_id"));

            ArrayList<Book> list = bookImpl.getBooksByGenre(genreId);

            request.setAttribute("currentBookList", list);
            request.getRequestDispatcher("/resources/jsp/books.jsp").forward(request, response);
        }
    }
}


