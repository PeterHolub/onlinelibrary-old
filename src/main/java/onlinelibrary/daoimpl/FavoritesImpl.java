package onlinelibrary.daoimpl;

import onlinelibrary.util.DatabaseConnection;

import java.sql.*;

public class FavoritesImpl {

    public void addToFavorites(String userId, int bookId) throws SQLException {

        Connection connection = null;
        connection = DatabaseConnection.getConnection();
        PreparedStatement statementBook = connection.prepareStatement("INSERT INTO favorites (user_id, book_id) VALUES (?, ?)");
        statementBook.setString(1, userId);
        statementBook.setInt(2, bookId);

        statementBook.executeUpdate();

        connection.close();

    }

    public void deleteFromFavorites(String userId, int bookId) throws SQLException {

        Connection connection = null;

        connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("DELETE FROM favorites WHERE user_id=? AND book_id=?");
        statement.setString(1, userId);
        statement.setInt(2, bookId);

        statement.executeUpdate();

        connection.close();

    }

    public boolean isBookAreFavorite(int bookId, String userId) throws SQLException {
        boolean result = false;

        ResultSet resultSet = null;
        Connection connection = null;
        connection = DatabaseConnection.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM favorites WHERE book_id=(?) AND user_id=(?)");
        preparedStatement.setInt(1, bookId);
        preparedStatement.setString(2, userId);
        resultSet = preparedStatement.executeQuery();

        if (resultSet.next()) {
            result = true;
        }

        connection.close();


        return result;

    }
}

