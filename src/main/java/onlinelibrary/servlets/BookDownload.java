package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;

import java.io.IOException;
import java.net.URLEncoder;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Servlet for sending pdf available for download  as response
@WebServlet("/BookDownload")
public class BookDownload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        BookImpl bookImpl = new BookImpl();
        int downloadId = Integer.valueOf(request.getParameter("downloadId"));

        String filename = bookImpl.getBookName(downloadId);
//encoding text for pdf file name
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8").replace("+", "%20") + ".pdf");

        response.getOutputStream().write(bookImpl.getBook(downloadId));


    }
}
