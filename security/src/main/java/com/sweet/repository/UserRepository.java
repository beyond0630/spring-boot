package com.sweet.repository;

import com.sweet.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/29
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
