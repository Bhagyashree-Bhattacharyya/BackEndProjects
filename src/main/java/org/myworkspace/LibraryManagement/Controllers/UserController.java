package org.myworkspace.LibraryManagement.Controllers;

import org.myworkspace.LibraryManagement.DTOs.UserRequest;
import org.myworkspace.LibraryManagement.Entities.User.User;
import org.myworkspace.LibraryManagement.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/filter")
    public List filter(@RequestParam("filterBy") String filterBy,
                       @RequestParam("operator") String operator,
                       @RequestParam("values") String values){
        return userService.filter(filterBy, operator, values);
    }
}
