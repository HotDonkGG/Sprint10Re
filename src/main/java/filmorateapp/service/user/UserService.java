package filmorateapp.service.user;

import filmorateapp.model.User;
import filmorateapp.storage.user.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Service
@RequestMapping("/users")
@Slf4j
public class UserService {
    private final UserStorage userStorage;

    @Autowired
    public UserService(UserStorage userStorage) {
        this.userStorage = userStorage;
    }

    public List<User> findAllUsers() {
        return userStorage.findAllUsers();
    }

    public User getUserById(int userId) {
        return userStorage.getUserById(userId);
    }

    public User addUser(User user) {
        return userStorage.addUser(user);
    }

    public User updateUser(User user) {
        return userStorage.updateUser(user);
    }

    public void addFriend(int userId, int friendId) {
        userStorage.addFriend(userId, friendId);
    }

    public void removeFriend(int userId, int friendId) {
        userStorage.removeFriend(userId, friendId);
    }

    public List<User> getFriendsByUserId(int userId) {
        return userStorage.getFriendsByUserId(userId);
    }

    public List<User> getMutualFriends(int userId, int friendId) {
        List<User> mutualFriendsList = new ArrayList<>(userStorage.getFriendsByUserId(userId));
        mutualFriendsList.retainAll(userStorage.getFriendsByUserId(friendId));
        return mutualFriendsList;
    }
}