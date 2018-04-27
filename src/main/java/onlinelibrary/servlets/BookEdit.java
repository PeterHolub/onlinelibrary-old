package onlinelibrary.servlets;

import onlinelibrary.daoimpl.*;
import onlinelibrary.models.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/BookEdit")
public class BookEdit extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        int editId = Integer.valueOf(request.getParameter("editId"));
        BookImpl bookImpl = new BookImpl();

        GenreImpl genreImpl = new GenreImpl();
        AuthorImpl authorImpl = new AuthorImpl();

//        ArrayList<Genre> genreList = genreImpl.getGenreList();
//        ArrayList<Author> authorList = authorImpl.getAuthorList();

        ArrayList<Book> bookList = bookImpl.getBookById(editId);
        request.setAttribute("bookEditList", bookList);
//        request.setAttribute("listGenres", genreList);
//        request.setAttribute("listAuthor", authorList);
        request.getRequestDispatcher("/resources/jsp/bookedit.jsp").forward(request, response);

    }
}




