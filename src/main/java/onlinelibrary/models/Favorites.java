package onlinelibrary.models;

import java.io.Serializable;
import java.util.Objects;

public class Favorites implements Serializable {
    private int id;
    private String userId;
    private int bookId;


    public Favorites() {
        }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Favorites favorites = (Favorites) o;
        return id == favorites.id &&
                bookId == favorites.bookId &&
                Objects.equals(userId, favorites.userId);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, userId, bookId);
    }

    @Override
    public String toString() {
        return "Favorites{" +
                "id=" + id +
                ", userId='" + userId + '\'' +
                ", bookId=" + bookId +
                '}';
    }
}
