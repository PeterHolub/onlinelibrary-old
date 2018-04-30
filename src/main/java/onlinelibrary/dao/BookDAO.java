package onlinelibrary.dao;

import onlinelibrary.enums.SearchType;
import onlinelibrary.models.Book;
import java.io.InputStream;
import java.util.ArrayList;

public interface BookDAO {
    ArrayList<Book> getBooks(String sql);

    ArrayList<Book> getAllBooks();

    ArrayList<Book> getBooksByGenre(int id);

    ArrayList<Book> getBooksBySearch(String searchStr, SearchType type);

    ArrayList<Book> getBooksByUserFavorites(String userFavorites);

    ArrayList<Book> getBookById(int id);

    void deleteBook(String bookName);

    byte[] getImage(int imageId);

    byte[] getBook(int bookId);

    String getBookName(int bookId);

    boolean uploadBook(String bookName, String description, InputStream inputStreamImage, InputStream inputStreamContent, int genre, int authorName);

    boolean bookUpdate(int genre, int authorName, String bookName, String description, int updateId, InputStream inputStreamImage, InputStream inputStreamContent);
}
