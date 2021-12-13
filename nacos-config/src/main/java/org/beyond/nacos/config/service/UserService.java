package org.beyond.nacos.config.service;

import org.beyond.nacos.config.model.entity.User;

/**
 * @author Beyond
 */
public interface UserService {

    /**
     * 查找用户
     *
     * @param id id
     * @return user
     */
    User getUser(int id);

}
