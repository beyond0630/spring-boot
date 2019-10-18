package com.sweet.repository;


import com.sweet.model.entities.User;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/18
 */
public interface UserRepository extends MongoRepository<User, Long> {

}
