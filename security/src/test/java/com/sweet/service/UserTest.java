package com.sweet.service;

import com.sweet.config.WebSecurityConfig;
import com.sweet.model.User;
import com.sweet.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/29
 */
@SpringBootTest
public class UserTest {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;

    @Test
    public void saveUser() {
        User user = User.newBuilder()
                .withUsername("user")
                .withPassword(encoder.encode("user"))
                .withMobile("1235456")
                .withRoles(WebSecurityConfig.ROLE_USER)
                .withStatus((byte) 1)
                .build();
        userRepository.save(user);
    }

    @Test
    public void getUser() {
        User admin = userRepository.findByUsername("admin");
        System.out.println(admin);
    }
}
