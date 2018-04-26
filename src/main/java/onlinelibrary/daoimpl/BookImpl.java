package onlinelibrary.daoimpl;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;

import onlinelibrary.models.Book;
import onlinelibrary.util.DatabaseConnection;
import onlinelibrary.enums.SearchType;
import org.apache.commons.io.IOUtils;

public class BookImpl {

    private ArrayList<Book> bookList = new ArrayList<>();

    private ArrayList<Book> getBooks(String sql) {

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;

        try {
            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();

            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getLong("id"));
                book.setName(resultSet.getString("name"));
                book.setGenre(resultSet.getString("genre"));
                book.setDescription(resultSet.getString("description"));
                book.setAuthor(resultSet.getString("author"));
                bookList.add(book);
                System.out.println(resultSet.getString("name"));
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return bookList;
    }

    public ArrayList<Book> getAllBooks() {
        return getBooks("select b.id, b.name,b.description,"
                + "a.authorname as author, g.name as genre from book b inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id order by b.name");
    }

    public ArrayList<Book> getBooksByGenre(long id) {
        if (id == 0) {
            return getAllBooks();
        } else {
            return getBooks("select b.id,b.name,b.description, a.authorname as author, g.name as genre from book b "
                    + "inner join author a on b.author_id=a.id "
                    + "inner join genre g on b.genre_id=g.id "
                    + "where genre_id=" + id + " order by b.name "
                    + "limit 0,5");
        }
    }

    public ArrayList<Book> getBooksBySearch(String searchStr, SearchType type) {
        StringBuilder sql = new StringBuilder("select b.id,b.name,b.description, a.authorname as author, g.name as genre from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id ");

        if (type == SearchType.AUTHOR) {
            sql.append("where lower(a.authorname) like '%" + searchStr.toLowerCase() + "%' order by b.name ");

        } else if (type == SearchType.TITLE) {
            sql.append("where lower(b.name) like '%" + searchStr.toLowerCase() + "%' order by b.name ");
        } else if (type == SearchType.GENRE) {
            sql.append("where lower(g.name) like '%" + searchStr.toLowerCase() + "%' order by b.name ");
        }
        sql.append("limit 0,5");

        return getBooks(sql.toString());
    }

    public ArrayList<Book> getBooksByUserFavorites(String userFavorites) {
        return getBooks("select b.id,b.name,b.description, a.authorname as author, g.name as genre from book b  "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join favorites f on b.id=f.book_id "
                + "where user_id='" + userFavorites + "'");
    }

    public ArrayList<Book> getBookById(int id) {
        return getBooks("select b.id,b.name,b.description, a.authorname as author, g.name as genre from book b inner join author a on b.author_id=a.id inner join genre g on b.genre_id=g.id where b.id=" + id + "");
    }

    public void deleteBook(String bookName) throws SQLException {
        Connection connection;
        connection = DatabaseConnection.getConnection();

        String sqlBook = "DELETE FROM book WHERE Id=?";

        PreparedStatement statement = connection.prepareStatement(sqlBook);
        statement.setString(1, bookName);

        statement.executeUpdate();
        connection.close();
    }

    public byte[] getImage(int imageId) throws SQLException {

        Connection connection;
        connection = DatabaseConnection.getConnection();
        byte[] image = new byte[0];
        PreparedStatement statement = connection.prepareStatement("SELECT image FROM book WHERE Id=?");

        statement.setInt(1, imageId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
            image = resultSet.getBytes("image");


        connection.close();
        return image;

    }

    public byte[] getBook(int bookId) throws SQLException {
        Connection connection;
        connection = DatabaseConnection.getConnection();
        byte[] book = new byte[0];
        PreparedStatement statement = connection.prepareStatement("SELECT content FROM book WHERE Id=?");

        statement.setInt(1, bookId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
            book = resultSet.getBytes("content");

        connection.close();
        return book;

    }

    public String getBookName(int bookId) throws SQLException {
        Connection connection;
        connection = DatabaseConnection.getConnection();
        String bookName = null;
        PreparedStatement statement = connection.prepareStatement("SELECT name FROM book WHERE Id=?");

        statement.setInt(1, bookId);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next())
            bookName = resultSet.getString("name");
        connection.close();
        return bookName;

    }

    public boolean uploadBook(String bookName, String description, InputStream inputStreamImage, InputStream inputStreamContent, int genre, int authorName) throws SQLException, IOException {

        boolean result;
        Connection connection;

        connection = DatabaseConnection.getConnection();

        String sqlBook = "INSERT INTO book (name, description,image, content, genre_id, author_id) VALUES (?, ?,?,?,?,?)";
        PreparedStatement statementBook = connection.prepareStatement(sqlBook);
        statementBook.setString(1, bookName);
        statementBook.setString(2, description);
        statementBook.setInt(5, genre);
        statementBook.setInt(6, authorName);

        if (inputStreamImage != null) {

            statementBook.setBytes(3, IOUtils.toByteArray(inputStreamImage));
            statementBook.setBytes(4, IOUtils.toByteArray(inputStreamContent));
        } else {
            System.out.println("inputStream is null");
        }
        int rowBook = statementBook.executeUpdate();

        result = rowBook > 0;
        connection.close();
        return result;
    }

    public boolean bookUpdate(int genre, int authorName, String bookName, String description, int updateId, InputStream inputStreamImage, InputStream inputStreamContent) throws SQLException, IOException {
        boolean result;
        Connection connection;

        connection = DatabaseConnection.getConnection();

        String sqlID = "UPDATE book SET genre_id =(?), author_id = (?), name =(?), description =(?) WHERE id =(?)";
        PreparedStatement statementID = connection.prepareStatement(sqlID);
        statementID.setInt(1, genre);
        statementID.setInt(2, authorName);
        statementID.setString(3, bookName);
        statementID.setString(4, description);
        statementID.setInt(5, updateId);

        if (inputStreamImage.available() > 0) {

            String sqlImage = "UPDATE book SET image =(?) WHERE id =(?)";
            PreparedStatement statementImage = connection.prepareStatement(sqlImage);
            statementImage.setBytes(1, IOUtils.toByteArray(inputStreamImage));
            statementImage.setInt(2, updateId);
            statementImage.executeUpdate();
        }

        if (inputStreamContent.available() > 0) {

            String sqlContent = "UPDATE book SET content =(?) WHERE id =(?)";
            PreparedStatement statementContent = connection.prepareStatement(sqlContent);
            statementContent.setBytes(1, IOUtils.toByteArray(inputStreamContent));
            statementContent.setInt(2, updateId);
            statementContent.executeUpdate();
        }

        int rowBook = statementID.executeUpdate();

        result = rowBook > 0;


        return result;
    }
}


