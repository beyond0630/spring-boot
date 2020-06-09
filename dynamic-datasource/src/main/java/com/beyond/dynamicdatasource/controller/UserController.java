package com.beyond.dynamicdatasource.controller;

import com.beyond.dynamicdatasource.entities.User;
import com.beyond.dynamicdatasource.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(final UserService userService) {this.userService = userService;}

    @GetMapping("/{id}/master")
    public User getUserFromMaster(@PathVariable int id) {
        return userService.getUserFromMaster(id);
    }

    @GetMapping("/{id}/slave")
    public User getUserFromSlave(@PathVariable int id) {
        return userService.getUserFromSlave(id);
    }

}
