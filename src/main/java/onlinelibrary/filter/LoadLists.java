package onlinelibrary.filter;

import onlinelibrary.daoimpl.AuthorImpl;
import onlinelibrary.daoimpl.GenreImpl;
import onlinelibrary.models.Author;
import onlinelibrary.models.Genre;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.util.*;

@WebFilter("/*")
public class LoadLists implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {

        GenreImpl genreImpl = new GenreImpl();
        AuthorImpl authorImpl = new AuthorImpl();

        ArrayList<Genre> genreList = genreImpl.getGenreList();
        ArrayList<Author> authorList = authorImpl.getAuthorList();

        request.getServletContext().setAttribute("listGenres", genreList);
        request.getServletContext().setAttribute("listAuthor", authorList);
        chain.doFilter(request, response);
    }

    public void init(FilterConfig config) throws ServletException {
    }

    public void destroy() {
    }
}
