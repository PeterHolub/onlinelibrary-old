package onlinelibrary.servlets;

import onlinelibrary.daoimpl.BookImpl;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;
//Update  values in database, using multipart and input stream
@WebServlet("/BookUpdate")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10,    // 10 MB
        maxFileSize = 1024 * 1024 * 50,        // 50 MB
        maxRequestSize = 1024 * 1024 * 100)    // 100 MB
public class BookUpdate extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {

        BookImpl bookImpl = new BookImpl();
        boolean uploadResult;
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


        int index = Integer.valueOf(request.getParameter("updateId"));


        uploadResult = bookImpl.bookUpdate(genre, authorName, bookName, description, index, inputStreamImage, inputStreamContent);


        if (uploadResult) {
            message = "File uploaded and saved into database";
        } else {
            message = "Failed to upload";
        }


        request.setAttribute("Message", message);
        request.getRequestDispatcher("/resources/jsp/message.jsp").forward(request, response);
    }
}
