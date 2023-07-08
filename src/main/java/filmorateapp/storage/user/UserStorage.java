package filmorateapp.storage.user;

import filmorateapp.model.User;

import java.util.List;

public interface UserStorage {
    User addUser(User user);

    User getUserById(int id);

    User updateUser(User user);

    void addFriend(int userId, int friendId);

    void removeFriend(int userId, int friendId);

    List<User> getMutualFriends(int id, int otherId);

    List<User> getFriendsByUserId(int id);

    List<User> findAllUsers();
}