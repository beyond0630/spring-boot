package com.beyond.dynamicdatasource.service;

import com.beyond.dynamicdatasource.entities.User;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
public interface UserService {

    User getUserFromMaster(int id);

    User getUserFromSlave(int id);
}
