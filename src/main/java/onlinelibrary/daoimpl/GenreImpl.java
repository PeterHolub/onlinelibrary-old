package onlinelibrary.daoimpl;

import java.sql.*;
import java.util.ArrayList;

import onlinelibrary.dao.GenreDAO;
import onlinelibrary.models.Genre;
import onlinelibrary.util.DatabaseConnection;

public class GenreImpl implements GenreDAO {
    @Override
    public ArrayList<Genre> getGenreList() {
        ArrayList<Genre> genreList = new ArrayList<>();
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM genre ORDER BY name");
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setName(resultSet.getString("name"));
                genre.setId(resultSet.getInt("id"));
                genreList.add(genre);
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

        return genreList;
    }


}

