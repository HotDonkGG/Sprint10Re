package filmorateapp.storage.user;

import filmorateapp.model.User;
import filmorateapp.model.exeption.NotFoundException;
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
    private final HashMap<Long, User> users = new HashMap<>();
    private long nextId = 0;

    /**
     * Добавление пользователя, возвращает user;
     */
    @Override
    public User addUser(User user) {
        validate(user);
        user.setFriends(new HashSet<>());
        user.setId(++nextId);
        users.put(user.getId(), user);
        log.info("User добавлен: " + user);
        return user;
    }

    /**
     * Получение пользователя по Id если есть, если пользователя нет возвращает ошибку;
     */
    @Override
    public User getUserById(long id) {
        if (users.containsKey(id)) {
            return users.get(id);
        } else throw new NotFoundException("User NotFound");
    }

    /**
     * Обновление пользователя по Id;
     */
    @Override
    public User updateUser(User user) {
        if (users.get(user.getId()) != null) {
            validate(user);
            user.setFriends(new HashSet<>());
            users.put(user.getId(), user);
            log.info("User обновлен");
        } else {
            log.error("User NotFound");
            throw new NotFoundException("User NotFound");
        }
        return user;
    }

    @Override
    public User addFriend(long userId, long friendId) {
        getUserById(userId).getFriends().add(friendId);
        getUserById(friendId).getFriends().add(userId);
        return getUserById(userId);
    }

    @Override
    public User removeFriend(long userId, long friendId) {
        getUserById(userId).getFriends().remove(friendId);
        getUserById(friendId).getFriends().remove(userId);
        return getUserById(userId);
    }

    @Override
    public List<User> getFriendsByUserId(long id) {
        return findAllUsers().stream()
                .filter(user -> user.getFriends().contains(id))
                .collect(Collectors.toList());
    }

    @Override
    public List<User> findAllUsers() {
        return new ArrayList<>(users.values());
    }

    @Override
    public List<User> getMutualFriends(long userId, long friendId) {
        List<User> mutualFriends = new ArrayList<>();
        for (Long id : getUserById(userId).getFriends()) {
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