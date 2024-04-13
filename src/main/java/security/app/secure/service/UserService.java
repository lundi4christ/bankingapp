package security.app.secure.service;

import org.springframework.security.core.userdetails.UserDetails;
import security.app.secure.entity.User;
import security.app.secure.tdto.UserDto;

import java.util.List;

public interface UserService {

    User saveUser (User userdata);
    User updateUser(long id, User userdata);
    User getUsersById(long id);
    UserDto findByUserDtoId(long id);
    List<User> getAllUser();
    List<UserDto> findByUserDto();
    void deleteUser(long id);
    User assignRoleToUser(String username, String roleName);
    User unassignRoleToUser(String username, String roleName);
    UserDetails getUserDetails(String username);

}
