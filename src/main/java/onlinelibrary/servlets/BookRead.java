package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;

import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
//Servlet for sending pdf as response
@WebServlet("/BookRead")
public class BookRead extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookImpl bookImpl = new BookImpl();

        int readId = Integer.valueOf(request.getParameter("readId"));
        response.setContentType("application/pdf");

        response.getOutputStream().write(bookImpl.getBook(readId));


    }
}
