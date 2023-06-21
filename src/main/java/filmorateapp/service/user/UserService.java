package filmorateapp.service.user;

import filmorateapp.model.User;
import filmorateapp.storage.user.UserStorage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

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

    public User addFriend(long userId, long friendId) {
        userStorage.addFriend(userId, friendId);
        return userStorage.getUserById(userId);
    }

    public User removeFriend(long userId, long friendId) {
        userStorage.removeFriend(userId, friendId);
        return userStorage.getUserById(userId);
    }

    public List<User> getMutualFriends(long userId, long otherId) {
        return userStorage.getMutualFriends(userId, otherId);
    }

    public List<User> getUserFriends(long userId) {
        return userStorage.getFriendsByUserId(userId);
    }
}