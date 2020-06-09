package com.beyond.dubbo.controller;

import java.util.List;

import com.alibaba.dubbo.config.annotation.Reference;
import com.beyond.dubbo.model.User;
import com.beyond.dubbo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/25
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Reference
    private UserService userService;

    @GetMapping("list")
    public List<User> listUser() {
        return userService.listUser();
    }

}
