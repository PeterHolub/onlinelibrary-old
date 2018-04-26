package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int imageId = Integer.valueOf(request.getParameter("imageId"));

        BookImpl bookImpl = new BookImpl();

        response.setContentType("image/jpeg");

        try {
            response.getOutputStream().write(bookImpl.getImage(imageId));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
}
