package onlinelibrary.daoimpl;

import onlinelibrary.dao.AuthorDAO;
import onlinelibrary.models.Author;
import onlinelibrary.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;

public class AuthorImpl implements AuthorDAO {

    @Override
    public ArrayList<Author> getAuthorList() {
        ArrayList<Author> authorList = new ArrayList<>();
        Connection connection;
        Statement statement = null;
        ResultSet resultSet = null;

        connection = DatabaseConnection.getConnection();

        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM author ORDER BY authorname");


            while (resultSet.next()) {
                Author author = new Author();
                author.setId(resultSet.getInt("id"));
                author.setAuthorname(resultSet.getString("authorname"));
                authorList.add(author);
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


        return authorList;
    }

}
