package id.go.kominfo.laila.mysqljdbcdemo.dao;

import java.util.List;
import id.go.kominfo.laila.mysqljdbcdemo.model.Friend;

public interface FriendDao {
    void insert(Friend f);
    void update(Friend f);
    void delete(int id);
    void deleteAllFriends();
    Friend getAFriendById(int id);
    List<Friend> getAllFriends();
}
