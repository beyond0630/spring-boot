package com.beyond.dubbo.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.alibaba.dubbo.config.annotation.Service;
import com.beyond.dubbo.model.User;
import org.springframework.stereotype.Component;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/25
 */
@Service
@Component
public class UserServiceImpl implements UserService {

    @Override
    public List<User> listUser() {
        return Arrays.stream(new int[]{ 1, 2, 3, 4, 5 })
                .mapToObj(x -> User.newBuilder()
                        .withId(x)
                        .withName("lucifer-" + x)
                        .withEmail("email-" + x)
                        .build()
                ).collect(Collectors.toList());
    }
}
