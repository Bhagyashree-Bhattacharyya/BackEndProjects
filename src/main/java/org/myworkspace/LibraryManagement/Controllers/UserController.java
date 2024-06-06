package org.myworkspace.LibraryManagement.Controllers;

import org.myworkspace.LibraryManagement.DTOs.UserRequest;
import org.myworkspace.LibraryManagement.Entities.User.User;
import org.myworkspace.LibraryManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/addConsumer")
    public User addConsumer(@RequestBody UserRequest userRequest){
        return userService.addConsumer(userRequest);
    }

    @PostMapping("/addAdmin")
    public User addAdmin(@RequestBody UserRequest userRequest){
        return null;
    }
}
