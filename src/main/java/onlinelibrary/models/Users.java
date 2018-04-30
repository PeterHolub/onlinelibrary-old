package onlinelibrary.models;

import java.io.Serializable;
import java.util.Objects;

public class Users  implements Serializable{

   private String userId;
   private  String password;
   private String group_id;

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserId() {

        return userId;
    }

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getPassword() {
        return password;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Users users = (Users) o;
        return Objects.equals(userId, users.userId) &&
                Objects.equals(password, users.password) &&
                Objects.equals(group_id, users.group_id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(userId, password, group_id);
    }

    @Override
    public String toString() {
        return "Users{" +
                "userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", group_id='" + group_id + '\'' +
                '}';
    }
}
