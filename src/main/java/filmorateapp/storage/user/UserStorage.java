package filmorateapp.storage.user;

import filmorateapp.model.User;

import java.util.HashMap;

public interface UserStorage {

    public User getUserById(int id);

    public HashMap<Integer,User> getAllUsers();

    public User addUser(User film);

    public User updateUser(User film);

    public User deleteUser(int id);

    public void deleteAllUsers();
}