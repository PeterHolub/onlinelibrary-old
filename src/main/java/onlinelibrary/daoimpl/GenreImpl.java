package onlinelibrary.daoimpl;

import java.sql.*;
import java.util.ArrayList;
import onlinelibrary.models.Genre;
import onlinelibrary.util.DatabaseConnection;

public class GenreImpl {

    private ArrayList<Genre> genreList = new ArrayList<Genre>();

    private ArrayList<Genre> getGenres() {

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = DatabaseConnection.getConnection();

            statement = connection.createStatement();
            resultSet = statement.executeQuery("select * from genre order by name");
            while (resultSet.next()) {
                Genre genre = new Genre();
                genre.setName(resultSet.getString("name"));
                genre.setId(resultSet.getLong("id"));
                genreList.add(genre);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();;
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (resultSet!= null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return genreList;
    }

    public ArrayList<Genre> getGenreList() {
        if (!genreList.isEmpty()) {
            return genreList;
        } else {
            return getGenres();
        }
    }
}

