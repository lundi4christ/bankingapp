package security.app.secure.tcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import security.app.secure.entity.User;
import security.app.secure.repository.RoleRepository;
import security.app.secure.repository.SavingsAccountRepository;
import security.app.secure.repository.UserRepository;
import security.app.secure.service.UserService;
import security.app.secure.tdto.LoginDto;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SavingsAccountRepository savingsAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("signin")
    public ResponseEntity<String> authenticateuser(@RequestBody LoginDto loginDto){

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User sign-in Successfully", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser (@RequestBody User datauser){
        // add check for username exists in DB
        if(userRepository.existsByUsername(datauser.getUsername())){
            return new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        }

        // add check for email exist in DB
        if(userRepository.existsByEmail(datauser.getEmail())){
            return new ResponseEntity<>("Email already exist", HttpStatus.BAD_REQUEST);
        }
        userService.saveUser(datauser);

        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);

    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userdata){
        userService.updateUser(id, userdata);
      return new ResponseEntity<>( HttpStatus.OK);

    }

    @GetMapping("/alluser")
    public List<User>  listAllUser(){
        return userService.getAllUser();
    }

    @GetMapping("/getuser/{id}")
    public User getUserById(@PathVariable long id){
    return userService.getUsersById(id);
//        return new ResponseEntity<User>(userService.getUsersById(id), HttpStatus.OK);
    }


    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){
        userService.deleteUser(id);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
