package onlinelibrary.dao;

import onlinelibrary.models.Users;
import java.util.ArrayList;

public interface UsersDAO {

    ArrayList<Users> getUsersList();

    void userRegistration(String username, String password, String groupId);

    String getGroupId(String username);
}
