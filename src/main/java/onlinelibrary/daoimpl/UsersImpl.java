package onlinelibrary.daoimpl;

import onlinelibrary.models.Users;
import onlinelibrary.util.DatabaseConnection;
import java.sql.*;
import java.sql.Statement;
import java.util.ArrayList;

public class UsersImpl {

    private ArrayList<Users> usersList = new ArrayList<>();

    public ArrayList<Users> getUsersList() throws SQLException {

        Statement statement = null;
        ResultSet resultSet = null;
        Connection connection;
        connection = DatabaseConnection.getConnection();

        statement = connection.createStatement();
        resultSet = statement.executeQuery("SELECT * FROM users ORDER BY userid");
        while (resultSet.next()) {
            Users users = new Users();
            users.setUserId(resultSet.getString("userid"));
            users.setPassword(resultSet.getString("password"));
            users.setGroup_id(resultSet.getString("group_id"));
            usersList.add(users);
        }
        connection.close();
        return usersList;
    }

    public void userRegistration(String username, String password, String groupId) throws SQLException {
        Connection connection;
        connection = DatabaseConnection.getConnection();
        PreparedStatement statement = connection.prepareStatement("INSERT INTO users(userid, password, group_id) VALUES (?,?,?)");

        statement.setString(1, username);
        statement.setString(2, password);
        statement.setString(3, groupId);
        statement.executeUpdate();
        connection.close();

    }

    public String getGroupId(String username) throws SQLException {
        Connection connection;
        connection = DatabaseConnection.getConnection();

        PreparedStatement statement = connection.prepareStatement("SELECT group_id FROM users WHERE userid=?");

        statement.setString(1, username);

        ResultSet resultSet = statement.executeQuery();
        String userRole = null;
        if (resultSet.next()) {
            userRole = resultSet.getString("group_id");
        }
        connection.close();

        return userRole;
    }


}



