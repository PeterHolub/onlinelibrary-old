package onlinelibrary.daoimpl;

import onlinelibrary.dao.UsersDAO;
import onlinelibrary.models.Users;
import onlinelibrary.util.DatabaseConnection;

import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersImpl implements UsersDAO {

    private ArrayList<Users> usersList = new ArrayList<>();
    private Connection connection;

    @Override
    public ArrayList<Users> getUsersList() {

        connection = DatabaseConnection.getConnection();

        Statement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.createStatement();

            resultSet = statement.executeQuery("SELECT * FROM users ORDER BY userid");
            while (resultSet.next()) {
                Users users = new Users();
                users.setUserId(resultSet.getString("userid"));
                users.setPassword(resultSet.getString("password"));
                users.setGroup_id(resultSet.getString("group_id"));
                usersList.add(users);
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

        return usersList;
    }

    @Override
    public void userRegistration(String username, String password, String groupId) {

        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = null;
        try {
            statement = connection.prepareStatement("INSERT INTO users(userid, password, group_id) VALUES (?,?,?)");


            statement.setString(1, username);
            statement.setString(2, password);
            statement.setString(3, groupId);
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
    public String getGroupId(String username) {

        connection = DatabaseConnection.getConnection();
        String userRole = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            statement = connection.prepareStatement("SELECT group_id FROM users WHERE userid=?");


            statement.setString(1, username);

            resultSet = statement.executeQuery();

            if (resultSet.next()) {
                userRole = resultSet.getString("group_id");
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


        return userRole;
    }


}



