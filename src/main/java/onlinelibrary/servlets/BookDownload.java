package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/BookDownload")
public class BookDownload extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
        BookImpl bookImpl = new BookImpl();
        int downloadId = Integer.valueOf(request.getParameter("downloadId"));

        String filename = null;
        try {
            filename = bookImpl.getBookName(downloadId);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            assert filename != null;
            response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, "UTF-8").replace("+", "%20") + ".pdf");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        try {
            response.getOutputStream().write(bookImpl.getBook(downloadId));
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }
}
