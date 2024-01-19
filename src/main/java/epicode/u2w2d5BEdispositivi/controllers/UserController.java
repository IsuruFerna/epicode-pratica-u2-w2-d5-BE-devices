package epicode.u2w2d5BEdispositivi.controllers;

import epicode.u2w2d5BEdispositivi.entities.User;
import epicode.u2w2d5BEdispositivi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    //get: users/
    @GetMapping("")
    public List<User> getUsers() {
        System.out.println(userService.getUsers());
        return userService.getUsers();
    }


}
