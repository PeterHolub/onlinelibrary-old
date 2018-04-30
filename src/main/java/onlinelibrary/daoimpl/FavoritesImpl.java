package onlinelibrary.daoimpl;

import onlinelibrary.dao.FavoritesDAO;
import onlinelibrary.models.Favorites;
import onlinelibrary.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class FavoritesImpl implements FavoritesDAO {

    private Connection connection = null;

    @Override
    public void addToFavorites(String userId, int bookId) {


        connection = DatabaseConnection.getConnection();

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO favorites (user_id, book_id) VALUES (?, ?)");

            statement.setString(1, userId);
            statement.setInt(2, bookId);

            statement.executeUpdate();

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
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void deleteFromFavorites(String userId, int bookId) {


        connection = DatabaseConnection.getConnection();

        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("DELETE FROM favorites WHERE user_id=? AND book_id=?");

            statement.setString(1, userId);
            statement.setInt(2, bookId);

            statement.executeUpdate();
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
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }


    }

    @Override
    public boolean isBookAreFavorite(int bookId, String userId) {
        boolean result = false;

        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM favorites WHERE book_id=(?) AND user_id=(?)");

            statement.setInt(1, bookId);
            statement.setString(2, userId);
            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                result = true;
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
        }
        return result;

    }

    @Override
    public ArrayList<Favorites> getFavorites(String userId) {

        ArrayList<Favorites> favoritesList = new ArrayList<>();
        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("SELECT * FROM favorites WHERE  user_id=(?)");


            statement.setString(1, userId);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {

                Favorites favorites = new Favorites();
                favorites.setUserId(resultSet.getString("user_id"));
                favorites.setBookId(resultSet.getInt("book_id"));
                favoritesList.add(favorites);

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
        }


        return favoritesList;


    }


}

