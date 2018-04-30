package onlinelibrary.filter;

import onlinelibrary.daoimpl.*;
import onlinelibrary.models.*;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.util.*;

//Loads Array lists for Genre menu and Authors & Genres drop lists in Book Edit jsp
@WebFilter("/*")
public class LoadLists implements Filter {
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws java.io.IOException, ServletException {

        GenreImpl genreImpl = new GenreImpl();
        AuthorImpl authorImpl = new AuthorImpl();

        ArrayList<Genre> genreList = genreImpl.getGenreList();

        ArrayList<Author> authorList = authorImpl.getAuthorList();

        request.setAttribute("listGenres", genreList);
        request.setAttribute("listAuthor", authorList);
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) {
    }

    @Override
    public void destroy() {
    }
}
