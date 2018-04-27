package onlinelibrary.daoimpl;

import onlinelibrary.models.Author;
import onlinelibrary.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;

public class AuthorImpl {

    private ArrayList<Author> authorList = new ArrayList<>();

    private ArrayList<Author> getAuthors() {

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection = null;
        try {

            connection = DatabaseConnection.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery("SELECT * FROM author ORDER BY authorname");

            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getLong("id"));
                author.setAuthorname(resultSet.getString("authorname"));
                authorList.add(author);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            try {
                if (statement != null) statement.close();
                if (resultSet != null) resultSet.close();
                if (connection != null) connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return authorList;
    }

    public ArrayList<Author> getAuthorList() {
        if (!authorList.isEmpty()) {
            return authorList;
        } else {
            return getAuthors();
        }
    }
}
