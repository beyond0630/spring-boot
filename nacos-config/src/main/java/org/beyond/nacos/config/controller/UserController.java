package org.beyond.nacos.config.controller;

import org.beyond.nacos.config.model.entity.User;
import org.beyond.nacos.config.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Beyond
 */
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) { this.userService = userService; }

    @GetMapping("{id}")
    public User getUser(@PathVariable int id) {
        return userService.getUser(id);
    }
}
