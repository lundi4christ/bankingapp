package security.app.secure.service;

import security.app.secure.entity.User;

import java.util.List;

public interface UserService {

    User saveUser (User userdata);
    User updateUser(long id, User userdata);
    User getUsersById(long id);
    List<User> getAllUser();
    void deleteUser(long id);

}
