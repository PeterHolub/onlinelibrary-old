package onlinelibrary.servlets;

import onlinelibrary.daoimpl.AuthorImpl;
import onlinelibrary.daoimpl.BookImpl;
import onlinelibrary.daoimpl.GenreImpl;
import onlinelibrary.enums.SearchType;
import onlinelibrary.models.Author;
import onlinelibrary.models.Book;
import onlinelibrary.models.Genre;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/BookServlet")


public class BookServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Book> list = null;
        BookImpl bookImpl = new BookImpl();

        request.getSession().setAttribute("selectedGenre", request.getParameter("genre_id"));

        //variations to show books by different criteria
        if (request.getParameter("genre_id") != null) {
            long genreId = Long.valueOf(request.getParameter("genre_id"));
            list = bookImpl.getBooksByGenre(genreId);


        } else if (request.getParameter("search_string") != null) {
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

//        } else if (request.getSession().getAttribute("currentBookList") != null) {
//            list = (ArrayList<Book>) request.getSession().getAttribute("currentBookList");
        } else {

            list = bookImpl.getAllBooks();

        }
        request.getSession().setAttribute("currentBookList", list);
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



        GenreImpl genreImpl = new GenreImpl();
        AuthorImpl authorImpl = new AuthorImpl();

        ArrayList<Genre> genreList = genreImpl.getGenreList();
        ArrayList<Author> authorList = authorImpl.getAuthorList();

        request.getServletContext().setAttribute("listGenres", genreList);
        request.getServletContext().setAttribute("listAuthor", authorList);




        request.getRequestDispatcher("/resources/jsp/books.jsp").forward(request, response);

    }
}
