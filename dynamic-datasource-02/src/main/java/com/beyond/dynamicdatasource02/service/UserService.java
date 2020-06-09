package com.beyond.dynamicdatasource02.service;

import com.beyond.dynamicdatasource02.entities.User;

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
