package th.mfu.boot;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    //static hasmap to store registered users
    public static Map<String, User> users = new HashMap<String, User>();

    //POST to register new users
   @PostMapping("/users")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
      //TODO 
      if (users.containsKey(user.getUsername())) {
        return new ResponseEntity<>("Username already exists", HttpStatus.CONFLICT);
      }
      users.put(user.getUsername(), user);
      return new ResponseEntity<>("User registered successfully", HttpStatus.CREATED);
    }

    //GET to list all the users
    @GetMapping("/users")
    public ResponseEntity<Collection<User>> list() {
        //TODO
        return new ResponseEntity<>(users.values(), HttpStatus.OK);
    }

    //GET to fetch specific user
    @GetMapping("/users/{username}")
    public ResponseEntity<User> getUser(String username) {
        //TODO
        User user = users.get(username);
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
    
}
