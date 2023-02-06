package com.mclebtec.demo.controller;

import com.mclebtec.demo.dto.UserDto;
import com.mclebtec.demo.exception.ResourceNotFoundException;
import com.mclebtec.demo.model.User;
import com.mclebtec.demo.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/v1/")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public List<UserDto> getAllUsers() {
        final List<User> userDetails = userRepository.findAll();
        log.info("getAllUsers::user-details::{}", userDetails);
        return userDetails.stream().map(user -> {
            UserDto userDto = new UserDto();
            userDto.setId(user.getId().toString());
            userDto.setFirstName(user.getFirstName());
            userDto.setLastName(user.getLastName());
            userDto.setEmailId(user.getEmailId());
            return userDto;
        }).collect(Collectors.toList());
    }

    @GetMapping("/users/{id}")
    public UserDto getUserById(@PathVariable String id) {
        log.info("getUserById::input-details::{}", id);
        User user = userRepository.findById(new ObjectId(id))
            .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        UserDto userDetail = new UserDto();
        userDetail.setId(user.getId().toString());
        userDetail.setFirstName(user.getFirstName());
        userDetail.setLastName(user.getLastName());
        userDetail.setEmailId(user.getEmailId());
        return userDetail;
    }

    @PostMapping("/users")
    public UserDto createUser(@RequestBody UserDto userDto) {
        log.info("createUser::input-details::{}", userDto);
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailId(userDto.getEmailId());
        User createdUser = userRepository.save(user);
        UserDto createdDetails = new UserDto();
        createdDetails.setId(createdUser.getId().toString());
        createdDetails.setFirstName(createdUser.getFirstName());
        createdDetails.setLastName(createdUser.getLastName());
        createdDetails.setEmailId(createdUser.getEmailId());
        return createdDetails;
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable String id,
                                              @RequestBody UserDto userDto) {
        log.info("updateUser::input-details::{}", userDto);
        User user = userRepository.findById(new ObjectId(id))
            .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmailId(userDto.getEmailId());
        User updatedUser = userRepository.save(user);
        UserDto updateUser = new UserDto();
        updateUser.setId(updatedUser.getId().toString());
        updateUser.setFirstName(updatedUser.getFirstName());
        updateUser.setLastName(updatedUser.getLastName());
        updateUser.setEmailId(updatedUser.getEmailId());
        return ResponseEntity.ok(updateUser);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String, Boolean>> deleteUser(@PathVariable String id) {
        log.info("deleteUser::input-details::{}", id);
        User user = userRepository.findById(new ObjectId(id))
            .orElseThrow(() -> new ResourceNotFoundException("User not exist with id :" + id));
        userRepository.delete(user);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }

}
