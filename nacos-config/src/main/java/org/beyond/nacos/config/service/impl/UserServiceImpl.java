package org.beyond.nacos.config.service.impl;

import org.beyond.nacos.config.model.entity.User;
import org.beyond.nacos.config.repository.UserRepository;
import org.beyond.nacos.config.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author Beyond
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUser(final int id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

}
