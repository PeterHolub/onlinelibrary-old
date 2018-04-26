package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/BookUpload")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,    // 10 MB
        maxFileSize = 1024 * 1024 * 80,        // 50 MB
        maxRequestSize = 1024 * 1024 * 100)    // 100 MB
public class BookUpload extends HttpServlet {


    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        BookImpl bookImpl = new BookImpl();
        boolean uploadResult = false;
        String message;
        String bookName = request.getParameter("book name");
        String description = request.getParameter("description");
        int authorName = Integer.parseInt(request.getParameter("author name"));
        int genre = Integer.parseInt(request.getParameter("genre"));

        InputStream inputStreamImage;
        InputStream inputStreamContent;

        Part filePart1 = request.getPart("image");
        Part filePart2 = request.getPart("content");

        inputStreamImage = filePart1.getInputStream();
        inputStreamContent = filePart2.getInputStream();


        try {
            uploadResult = bookImpl.uploadBook(bookName, description, inputStreamImage, inputStreamContent, genre, authorName);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (uploadResult) {
            message = "File uploaded and saved into database";
        } else {
            message = "Failed to upload";
        }

        request.setAttribute("Message", message);
        request.getRequestDispatcher("/resources/jsp/message.jsp").forward(request, response);
    }
}
