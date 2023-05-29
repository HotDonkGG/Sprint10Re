package filmorateapp.model.controller;

import filmorateapp.model.User;
import filmorateapp.model.validation.ValidationService;
import filmorateapp.service.user.UserService;
import filmorateapp.storage.user.UserStorage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping(value = "/users", produces = "application/json")
public class UserController {

    private final UserService userService;
    private final UserStorage userStorage;
    private final ValidationService validationService;

    @PostMapping
    public User addUser(@Validated @RequestBody User user) {
        log.info("Поступил запрос на создание пользователя.");
        return userStorage.addUser(user);
    }

    @PutMapping
    public User updateUser(@Validated @RequestBody long id, @RequestBody User user) {
        log.info("Поступил запрос на обновление пользователя.");
        return userStorage.updateUser(id, user);
    }

    @PutMapping("/{id}/friends/{friendId}")
    public User addFriend(@Validated @PathVariable User id, @PathVariable long friendId) {
        log.info("Поступил запрос на добавления в друзья.");
        return userService.addFriend(id, friendId);
    }

    @GetMapping
    public List<User> getAllUsers() {
        log.info("Поступил запрос на получение списка пользователей.");
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public User getUserById(@Validated @PathVariable String id) {
        log.info("Поступил запрос на получение пользователя по id.");
        return userStorage.getUserById(Integer.parseInt(id));
    }

    @GetMapping("/{id}/friends")
    public List<User> getFriends(@Validated @PathVariable long id) {
        log.info("Поступил запрос на получение списка друзей.");
        return userService.getUserFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")
    public List<User> getMutualFriends(@Validated @PathVariable String id, @PathVariable String otherId) {
        log.info("Поступил запрос на получения списка общих друзей.");
        return userService.getMutualFriends(Integer.parseInt(id), Integer.parseInt(otherId));
    }

    @DeleteMapping("/{id}/friends/{friendId}")
    public void deleteFriend(@Validated @PathVariable User id, @PathVariable long friendId) {
        log.info("Поступил запрос на удаление из друзей.");
        userService.removeFriend(id, friendId);
    }
}