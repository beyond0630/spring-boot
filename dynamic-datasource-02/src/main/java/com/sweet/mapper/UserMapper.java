package com.sweet.mapper;

import com.sweet.entities.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2020/4/28
 */
@Mapper
public interface UserMapper {

    User getUser(int id);

}
