package security.app.secure.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import security.app.secure.entity.Role;
import security.app.secure.entity.SavingsAccount;
import security.app.secure.entity.User;
import security.app.secure.exception.ResourceNotFoundException;
import security.app.secure.repository.RoleRepository;
import security.app.secure.repository.UserRepository;
import security.app.secure.service.UserService;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private static int autoincreaseno = 202030205;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public User saveUser(User datauser) {
        SavingsAccount savingsAccount = new SavingsAccount();
        savingsAccount.setAccountno(autono());
        savingsAccount.setAccount_balance(new BigDecimal(0.00));

        Role roles = roleRepository.findByName("ROLE_ADMIN").get();
        datauser.setRoles(Collections.singleton(roles));

        // set parent reference (user) in child entity(savingsAccount)
        savingsAccount.setUser(datauser);
        // set child reference (savingsAccount) in parent entity(user)
        datauser.setSavingsAccount(savingsAccount);

        datauser.setPassword(passwordEncoder.encode(datauser.getPassword()));

        // Save Parent Reference (which will save the child as well)
        userRepository.save(datauser);

        return datauser;
    }

    @Override
    public User updateUser(long id, User userdata) {
        User existuser = userRepository.findById(id).orElse(null);

        existuser.setUsername(userdata.getUsername());
        existuser.setEmail(userdata.getEmail());
        existuser.setName(userdata.getName());
        existuser.setName(userdata.getPassword());

        userRepository.save(existuser);
        return existuser;
    }

    @Override
    public User getUsersById(long id) {
        return userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", "id"));
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public void deleteUser(long id) {
        userRepository.findById(id).orElse(null);
        userRepository.deleteById(id);
    }

   // Assign roles to user
    @Override
    public User assignRoleToUser(String username, String roleName) {
        User user = userRepository.findByUsername(username).orElse(null);
        System.out.println("uuuuuuu " + user.getUsername());
        Role roles = roleRepository.findByName(roleName).orElse(null);
        System.out.println("rrrrrrrrrr " + roles.getName());

        if (user == null || roles == null){
            throw new IllegalArgumentException("User or Role not found");
        }

        user.getRoles().add(roles);

        userRepository.save(user);
        return user;
    }

    public static int autono() {
        return ++autoincreaseno;
    }

   /* @Override
    public User assignRoleToUser(String username, String roleName){
        User user = userRepository.findByUsername(username).orElse(null);
        System.out.println("uuuuuuu " + user);
        Role roles = roleRepository.findByName(roleName).orElse(null);

        System.out.println ("uuuuuuuuuuuu " + user + " - "  + roles);

        if (user == null || roles == null){
            throw new IllegalArgumentException("User or Role not found");
        }

        user.getRoles().add(roles);

        userRepository.save(user);
        return user;
    }*/
}
