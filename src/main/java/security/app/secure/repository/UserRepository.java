package security.app.secure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import security.app.secure.entity.User;
import security.app.secure.tdto.UserDto;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
    Optional<User> findByUsernameOrEmail(String username, String email);
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
    @Query("SELECT new security.app.secure.tdto.UserDto(u.id, u.email, u.name, u.password, u.username) FROM User u WHERE u.id = :userId")
    UserDto findByUserDtoId(@Param("userId")Long id);
    @Query("SELECT new security.app.secure.tdto.UserDto(u.id, u.email, u.name, u.password, u.username) FROM User u")
    List<UserDto> findByUserDto();

}
