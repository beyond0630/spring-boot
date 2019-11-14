package com.sweet.controller.common;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import model.User;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/11/14
 */
public class UserController {

    @ApiOperation("查询用户")
    @GetMapping("/{id}")
    public User getUser(@ApiParam(value = "用户Id", required = true) @PathVariable int id) {
        return User.newBuilder()
                .withId(id)
                .withName("lucifer")
                .build();
    }

    @PutMapping("/{id}/update")
    public User updateUser(@ApiParam(value = "用户Id", required = true) @PathVariable int id) {
        return User.newBuilder()
                .withId(id)
                .withName("lucifer")
                .build();
    }

    @DeleteMapping("/{id}/delete")
    public User deleteUser(@ApiParam(value = "用户Id", required = true) @PathVariable int id) {
        return User.newBuilder()
                .withId(id)
                .withName("lucifer")
                .build();
    }
}
