package org.myworkspace.LibraryManagement.Services;

import org.myworkspace.LibraryManagement.DTOs.UserRequest;
import org.myworkspace.LibraryManagement.Entities.User.User;
import org.myworkspace.LibraryManagement.Entities.User.UserType;
import org.myworkspace.LibraryManagement.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User addConsumer(UserRequest userRequest){
        User user = userRequest.toUser();
        user.setUserType(UserType.CONSUMER);
        return userRepository.save(user);
    }
}
