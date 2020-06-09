package com.beyond.dynamicdatasource02.service.impl;

import com.beyond.dynamicdatasource02.annotation.DataSource;
import com.beyond.dynamicdatasource02.mapper.UserMapper;
import com.beyond.dynamicdatasource02.service.UserService;
import com.beyond.dynamicdatasource02.entities.User;
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
