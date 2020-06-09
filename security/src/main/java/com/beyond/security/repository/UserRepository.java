package com.beyond.security.repository;

import com.beyond.security.model.User;
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
