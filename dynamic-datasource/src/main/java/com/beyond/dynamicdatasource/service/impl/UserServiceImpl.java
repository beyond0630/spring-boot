package com.beyond.dynamicdatasource.service.impl;

import com.beyond.dynamicdatasource.annotation.DataSource;
import com.beyond.dynamicdatasource.entities.User;
import com.beyond.dynamicdatasource.enums.DataSourceKey;
import com.beyond.dynamicdatasource.mapper.UserMapper;
import com.beyond.dynamicdatasource.service.UserService;
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
    @DataSource(DataSourceKey.SLAVE)
    public User getUserFromSlave(int id) {
        return userMapper.getUser(id);
    }
}
