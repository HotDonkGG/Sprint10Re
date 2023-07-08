package filmorateapp.storage.user;

import filmorateapp.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.validation.ValidationException;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {
    private final HashMap<Integer, User> users = new HashMap<>();

    /**
     * Добавление пользователя, возвращает user;
     */
    @Override
    public User addUser(User user) {
        user.generateAndSetId();
        user.generateOfSetFriends();
        users.put(user.getId(), user);
        log.info("User добавлен: " + user);
        return user;
    }

    /**
     * Получение пользователя по Id если есть, если пользователя нет возвращает ошибку;
     */
    @Override
    public User getUserById(int userId) {
        log.info("Get userById: " + userId);
        return users.get(userId);
    }

    /**
     * Обновление пользователя по Id;
     */
    @Override
    public User updateUser(User user) {
        user.setFriends(users.get(user.getId()).getFriends());
        users.put(user.getId(), user);
        log.info("User обновлен");
        return user;
    }

    @Override
    public void addFriend(int userId, int friendId) {
        users.get(userId).addFriend(friendId);
        users.get(friendId).addFriend(userId);
    }

    @Override
    public void removeFriend(int userId, int friendId) {
        users.get(userId).removeFriend(friendId);
        users.get(friendId).removeFriend(userId);
    }

    @Override
    public List<User> getFriendsByUserId(int userId) {
        return findAllUsers().stream()
                .filter(user -> user.getFriends().contains(userId))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAllUsers() {
        log.info("получить список всех пользователей");
        return new ArrayList<>(users.values());
    }

    @Override
    public List<User> getMutualFriends(int userId, int friendId) {
        List<User> mutualFriends = new ArrayList<>();
        for (int id : getUserById(userId).getFriends()) {
            if (getUserById(friendId).getFriends().contains(id)) {
                mutualFriends.add(getUserById(id));
            }
        }
        return mutualFriends;
    }

    public void validate(User user) throws ValidationException {
        if (user.getEmail() == null || user.getEmail().isEmpty() || !user.getEmail().contains("@")) {
            throw new javax.validation.ValidationException("Емейл юзера не может быть пустым и должен содержать @");
        } else if (user.getLogin() == null || user.getLogin().contains(" ")) {
            throw new javax.validation.ValidationException("Логин пользователя не может быть пустым или содержать пробелы");
        } else if (user.getBirthday() != null && user.getBirthday().isAfter(ChronoLocalDate.from(LocalDateTime.now()))) {
            throw new ValidationException("Пользователь не может быть рождён в будущем");
        }
    }
}