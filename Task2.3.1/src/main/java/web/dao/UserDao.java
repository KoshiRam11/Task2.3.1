package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUser();
    User getUser(Long id);
    void addUser(User user);
    void removeUser(Long id);
    void updateUser(User newUser);


}
