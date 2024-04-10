package security.app.secure.service;

import org.springframework.security.core.userdetails.UserDetails;
import security.app.secure.entity.User;

import java.util.List;

public interface UserService {

    User saveUser (User userdata);
    User updateUser(long id, User userdata);
    User getUsersById(long id);
    List<User> getAllUser();
    void deleteUser(long id);
    User assignRoleToUser(String username, String roleName);
    User unassignRoleToUser(String username, String roleName);
    UserDetails getUserDetails(String username);

}
