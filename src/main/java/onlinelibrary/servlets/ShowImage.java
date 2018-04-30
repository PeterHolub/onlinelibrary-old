package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;
import java.io.IOException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

//Servlet for sending Images as response
@WebServlet("/ShowImage")
public class ShowImage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        int imageId = Integer.valueOf(request.getParameter("imageId"));

        BookImpl bookImpl = new BookImpl();

        response.setContentType("image/jpeg");

        response.getOutputStream().write(bookImpl.getImage(imageId));

        }
}
