package com.sweet.service;

import com.sweet.model.entities.User;
import org.springframework.data.domain.Page;

public interface UserService {

    User getUser(long id);

    void saveOrUpdateUser(User user);

    Page<User> listPageUsers(int page, int size);
}
