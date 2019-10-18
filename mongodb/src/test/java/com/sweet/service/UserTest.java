package com.sweet.service;

import com.sweet.generator.IdGenerator;
import com.sweet.model.entities.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/18
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;
    @Autowired
    private IdGenerator idGenerator;

    @Test
    void saveUser() {
        for (int i = 0; i <= 100; i++) {
            userService.saveOrUpdateUser(User.newBuilder()
                    .withId(idGenerator.nextId())
                    .withAccount("lucifer" + (i + 1))
                    .withUsername("lucifer" + (i + 1))
                    .withEmail("lucifer email")
                    .build());
        }
    }

    @Test
    void updateUser() {
        userService.saveOrUpdateUser(User.newBuilder()
                .withId(1310077078208549L)
                .withUsername("sweet")
                .build());
    }
}
