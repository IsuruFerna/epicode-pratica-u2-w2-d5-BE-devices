package epicode.u2w2d5BEdispositivi.controllers;

import epicode.u2w2d5BEdispositivi.entities.User;
import epicode.u2w2d5BEdispositivi.exceptions.BadRequestException;
import epicode.u2w2d5BEdispositivi.payload.NewUserDTO;
import epicode.u2w2d5BEdispositivi.payload.NewUserResponse;
import epicode.u2w2d5BEdispositivi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

//    get: users/
    @GetMapping("")
    public Page<User> getUsers(@RequestParam(defaultValue = "0") int page,
                               @RequestParam(defaultValue = "10") int size,
                               @RequestParam(defaultValue = "id") String sort) {
        return userService.getUsers(page, size, sort);
    }

//    post: users/
    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    public NewUserResponse saveUser(@RequestBody @Validated NewUserDTO body, BindingResult validation) {
        if(validation.hasErrors()) {
            throw new BadRequestException(validation.getAllErrors());
        }
        User user = userService.save(body);
        return new NewUserResponse(user.getId());
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

//    patch: users/{userid}/avatar
    @PatchMapping("/{userId}/avatar")
    public User uploadAvatar(@RequestParam("avatar")MultipartFile file, @PathVariable Long userId) {
        try {
            return userService.uploadAvatar(userId, file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
