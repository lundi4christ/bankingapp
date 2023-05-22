package security.app.secure.tcontroller;

import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import security.app.secure.entity.SavingsAccount;
import security.app.secure.entity.User;
import security.app.secure.repository.RoleRepository;
import security.app.secure.repository.SavingsAccountRepository;
import security.app.secure.repository.UserRepository;
import security.app.secure.service.UserService;
import security.app.secure.tdto.LoginDto;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    public ResponseEntity<String> authenticateuser(@RequestBody LoginDto loginDto) {

        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(), loginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new ResponseEntity<>("User sign-in Successfully", HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveUser(@RequestBody User datauser) {
        // add check for username exists in DB
        if (userRepository.existsByUsername(datauser.getUsername())) {
            return new ResponseEntity<>("Username already exist", HttpStatus.BAD_REQUEST);
        }

        // add check for email exist in DB
        if (userRepository.existsByEmail(datauser.getEmail())) {
            return new ResponseEntity<>("Email already exist", HttpStatus.BAD_REQUEST);
        }
        System.out.println("******** " + datauser);
        userService.saveUser(datauser);

        return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);

    }

    // to create a user using json file data // in postman using form-data and make sure the key is file: creatuser.json and a header
    // content-type: multipart/form-data
    @PostMapping("/uploadjson")
        public ResponseEntity<?> saveJson(@RequestParam("file") MultipartFile file) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        User duser = mapper.readValue(file.getBytes(), User.class);
            System.out.println("*************** " + duser);

            if(userRepository.existsByUsername(duser.getUsername())){
                return new ResponseEntity<>("username already exist", HttpStatus.BAD_REQUEST);
            }
            if(userRepository.existsByEmail(duser.getEmail())){
                return new ResponseEntity<>("email already exist", HttpStatus.BAD_REQUEST);
            }
            userService.saveUser(duser);
            return new ResponseEntity<>("User Registered Successfully", HttpStatus.OK);
        }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userdata) {
        userService.updateUser(id, userdata);
        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/assignroletouser/{username}/roles/{roleName}")
    public ResponseEntity<User> assignRoleToUser (@PathVariable String username, @PathVariable String roleName){
        System.out.println ("ddddddddd " + username + " - "  + roleName);

        userService.assignRoleToUser(username, roleName);

        return new ResponseEntity<>(HttpStatus.OK);

    }

    @PostMapping("/unassignroletouser/{username}/roles/{roleName}")
    public ResponseEntity<User> unassignRoleToUser(@PathVariable String username, @PathVariable String roleName){

        userService.unassignRoleToUser(username, roleName);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/alluser")
    public List<User> listAllUser() {

        return userService.getAllUser();
    }

    @GetMapping("/getuser/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getUsersById(id);
//        return new ResponseEntity<User>(userService.getUsersById(id), HttpStatus.OK);
    }

    @DeleteMapping("/deleteuser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>("user deleted", HttpStatus.OK);
    }

    @GetMapping("/resource")
    public Map<String, Object> mapresource(User dusers, Principal principal) {
        User users = userRepository.findByUsername(dusers.getUsername()).orElse(null);
        Map<String, Object> model = new HashMap<>();
        model.put("username", users.getUsername());
        model.put("email", users.getEmail());
        model.put("name", users.getName());
        model.put("account", users.getSavingsAccount());

        return (Map<String, Object>) users;
    }

    @GetMapping("/getaccount")
    public Map<String, Object> mapaccount(SavingsAccount accts){
//        SavingsAccount savingsaccts = savingsAccountRepository.findOne(accts).orElse(null);

        return (Map<String, Object>) mapaccount(accts);
    }
}
