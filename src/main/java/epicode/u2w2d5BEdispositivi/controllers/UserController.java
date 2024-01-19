package epicode.u2w2d5BEdispositivi.controllers;

import epicode.u2w2d5BEdispositivi.entities.User;
import epicode.u2w2d5BEdispositivi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

//    get: users/
    @GetMapping("")
    public List<User> getUsers() {
        return userService.getUsers();
    }

//    post: users/
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public User saveUser(@RequestBody User body) {
        return userService.save(body);
    }

//    get: users/userId
    @GetMapping("/{userId}")
    @ResponseStatus(HttpStatus.FOUND)
    public User findById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

//    put: users/userId
    @PutMapping("/{userId}")
    public User findByIdAndUpdate(@PathVariable Long userId, @RequestBody User body) {
        return userService.findByIdAndUpdate(userId, body);
    }

//    delete: users/userId
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void findByIdAndDelete(@PathVariable Long userId) {
        userService.findByIdAndDelete(userId);
    }


}
