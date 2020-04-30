package com.sweet.service.impl;

import com.sweet.annotation.DataSource;
import com.sweet.entities.User;
import com.sweet.mapper.UserMapper;
import com.sweet.service.UserService;
import org.springframework.stereotype.Service;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;

    public UserServiceImpl(final UserMapper userMapper) {this.userMapper = userMapper;}

    @Override
    @DataSource
    public User getUserFromMaster(int id) {
        return userMapper.getUser(id);
    }

    @Override
    @DataSource(id = 1)
    public User getUserFromSlave(int id) {
        return userMapper.getUser(id);
    }
}
