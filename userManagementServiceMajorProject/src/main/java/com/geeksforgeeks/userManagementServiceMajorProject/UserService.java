package com.geeksforgeeks.userManagementServiceMajorProject;

import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    KafkaTemplate kafkaTemplate;
    public void create(UserRequest userRequest){
        User user = new User();
        user.setName(userRequest.getName());
        user.setEmail(userRequest.getEmail());
        user.setPhone(userRequest.getPhone());
        user.setUsername(userRequest.getUsername());

        Event event=new Event();
        userRepository.save(user);
        event.setName("USER_CREATED");
        event.setUser(user.getUsername());
        event.setData("user created");
        kafkaTemplate.send("USER",event);
    }

    public UserResponse get(String username) throws NotFoundException {
        User user=userRepository.findUserByUsername(username)
                .orElseThrow(()->new NotFoundException("User doesn't exist !!"));
        UserResponse userResponse= new UserResponse();
        userResponse.setName(user.getName());
        userResponse.setEmail(user.getEmail());
        userResponse.setUsername(user.getUsername());
        userResponse.setPhone(user.getPhone());
        return userResponse;
    }

    public void update(UserRequest userRequest,String userName) throws NotFoundException {
        User user=userRepository.findUserByUsername(userName)
                .orElseThrow(()->new NotFoundException("User doesn't exist !!"));

        if(Objects.nonNull(userRequest.getEmail()))
            user.setEmail(user.getEmail());

        if(Objects.nonNull(userRequest.getPhone()))
            user.setPhone(userRequest.getPhone());

        userRepository.save(user);

        Event event=new Event();
        event.setName("USER_UPDATED");
        event.setUser(user.getUsername());
        event.setData("user updated");
        kafkaTemplate.send("USER",event);
    }
}
