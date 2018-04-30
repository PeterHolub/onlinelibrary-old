package onlinelibrary.dao;

import onlinelibrary.models.Favorites;

import java.util.ArrayList;

public interface FavoritesDAO {

    void addToFavorites(String userId, int bookId);

    void deleteFromFavorites(String userId, int bookId);

    boolean isBookAreFavorite(int bookId, String userId);

    ArrayList<Favorites> getFavorites(String userId);
}
