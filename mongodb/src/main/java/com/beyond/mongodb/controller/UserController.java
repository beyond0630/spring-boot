package com.beyond.mongodb.controller;

import com.beyond.mongodb.model.entities.User;
import com.beyond.mongodb.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/18
 */
@RestController
@RequestMapping("/api/user/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {this.userService = userService;}

    @GetMapping("{id}")
    public User getUser(@PathVariable long id) {
        return userService.getUser(id);
    }

    @GetMapping("/list/{page}/{size}")
    public Page<User> listPageUsers(@PathVariable int page, @PathVariable int size) {
        return userService.listPageUsers(page, size);
    }

}
