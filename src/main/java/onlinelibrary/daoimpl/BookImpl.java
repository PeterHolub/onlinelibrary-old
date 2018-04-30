package onlinelibrary.daoimpl;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;

import onlinelibrary.dao.BookDAO;
import onlinelibrary.models.Book;
import onlinelibrary.util.DatabaseConnection;
import onlinelibrary.enums.SearchType;
import org.apache.commons.io.IOUtils;

public class BookImpl implements BookDAO {

    private ArrayList<Book> bookList = new ArrayList<>();
    private Connection connection = null;

    @Override
    public ArrayList<Book> getBooks(String sql) {

        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();

        try {
            statement = connection.createStatement();


            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Book book = new Book();
                book.setId(resultSet.getInt("id"));
                book.setName(resultSet.getString("name"));
                book.setGenre(resultSet.getString("genre"));
                book.setDescription(resultSet.getString("description"));
                book.setAuthor(resultSet.getString("author"));
                bookList.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return bookList;
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return getBooks("select b.id, b.name,b.description,"
                + "a.authorname as author, g.name as genre from book b inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id order by b.name");
    }

    @Override
    public ArrayList<Book> getBooksByGenre(int id) {
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

    @Override
    public ArrayList<Book> getBooksBySearch(String searchStr, SearchType type) {
        StringBuilder sql = new StringBuilder("select b.id,b.name,b.description, a.authorname as author, g.name as genre from book b "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id ");

        if (type == SearchType.AUTHOR) {
            sql.append("where lower(a.authorname) like '%").append(searchStr.toLowerCase()).append("%' order by b.name ");

        } else if (type == SearchType.TITLE) {
            sql.append("where lower(b.name) like '%").append(searchStr.toLowerCase()).append("%' order by b.name ");
        } else if (type == SearchType.GENRE) {
            sql.append("where lower(g.name) like '%").append(searchStr.toLowerCase()).append("%' order by b.name ");
        }
        sql.append("limit 0,5");

        return getBooks(sql.toString());
    }

    @Override
    public ArrayList<Book> getBooksByUserFavorites(String userFavorites) {
        return getBooks("select b.id,b.name,b.description, a.authorname as author, g.name as genre from book b  "
                + "inner join author a on b.author_id=a.id "
                + "inner join genre g on b.genre_id=g.id "
                + "inner join favorites f on b.id=f.book_id "
                + "where user_id='" + userFavorites + "'");
    }

    @Override
    public ArrayList<Book> getBookById(int id) {
        return getBooks("select b.id,b.name,b.description, a.authorname as author, g.name as genre from book b inner join author a on b.author_id=a.id inner join genre g on b.genre_id=g.id where b.id=" + id + "");
    }

    @Override
    public void deleteBook(String bookName) {

        connection = DatabaseConnection.getConnection();

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM book WHERE Id=?");

            statement.setString(1, bookName);

            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (statement != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public byte[] getImage(int imageId) {

        connection = DatabaseConnection.getConnection();

        byte[] image = new byte[0];

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT image FROM book WHERE Id=?");


            statement.setInt(1, imageId);

            resultSet = statement.executeQuery();

            if (resultSet.next())
                image = resultSet.getBytes("image");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            if (resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            if (resultSet != null)
                statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return image;

    }

    @Override
    public byte[] getBook(int bookId) {

        connection = DatabaseConnection.getConnection();
        byte[] book = new byte[0];

        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT content FROM book WHERE Id=?");


            statement.setInt(1, bookId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                book = resultSet.getBytes("content");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return book;

    }

    @Override
    public String getBookName(int bookId) {

        connection = DatabaseConnection.getConnection();
        String bookName = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT name FROM book WHERE Id=?");


            statement.setInt(1, bookId);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                bookName = resultSet.getString("name");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {


            try {
                if (resultSet != null)
                    resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return bookName;

    }

    @Override
    public boolean uploadBook(String bookName, String description, InputStream inputStreamImage, InputStream inputStreamContent, int genre, int authorName) {

        boolean result = false;

        connection = DatabaseConnection.getConnection();


        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO book (name, description,image, content, genre_id, author_id) VALUES (?,?,?,?,?,?)");

            statement.setString(1, bookName);
            statement.setString(2, description);
            statement.setInt(5, genre);
            statement.setInt(6, authorName);

            if (inputStreamImage != null) {

                statement.setBytes(3, IOUtils.toByteArray(inputStreamImage));
                statement.setBytes(4, IOUtils.toByteArray(inputStreamContent));
            }

            int rowBook = statement.executeUpdate();

            result = rowBook > 0;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return result;
    }

    @Override
    public boolean bookUpdate(int genre, int authorName, String bookName, String description, int updateId, InputStream inputStreamImage, InputStream inputStreamContent) {
        boolean result = false;

        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("UPDATE book SET genre_id =(?), author_id = (?), name =(?), description =(?) WHERE id =(?)");

            statement.setInt(1, genre);
            statement.setInt(2, authorName);
            statement.setString(3, bookName);
            statement.setString(4, description);
            statement.setInt(5, updateId);

            if (inputStreamImage.available() > 0) {

                PreparedStatement statementImage = connection.prepareStatement("UPDATE book SET image =(?) WHERE id =(?)");
                statementImage.setBytes(1, IOUtils.toByteArray(inputStreamImage));
                statementImage.setInt(2, updateId);
                statementImage.executeUpdate();
                statementImage.close();
            }

            if (inputStreamContent.available() > 0) {

                PreparedStatement statementContent = connection.prepareStatement("UPDATE book SET content =(?) WHERE id =(?)");
                statementContent.setBytes(1, IOUtils.toByteArray(inputStreamContent));
                statementContent.setInt(2, updateId);
                statementContent.executeUpdate();
                statementContent.close();
            }

            int rowBook = statement.executeUpdate();

            result = rowBook > 0;

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null)
                    statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


        return result;
    }
}


