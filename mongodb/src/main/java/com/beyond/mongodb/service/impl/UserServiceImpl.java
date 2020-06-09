package com.beyond.mongodb.service.impl;

import com.beyond.mongodb.exception.DocumentNotFoundException;
import com.beyond.mongodb.model.entities.User;
import com.beyond.mongodb.repository.UserRepository;
import com.beyond.mongodb.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/18
 */
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public User getUser(long id) {
        return userRepository.findById(id).orElseThrow(DocumentNotFoundException::new);
    }

    @Override
    public void saveOrUpdateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public Page<User> listPageUsers(int page, int size) {
        return userRepository.findAll(PageRequest.of(page - 1, size, Sort.by("id").descending()));
    }

}
