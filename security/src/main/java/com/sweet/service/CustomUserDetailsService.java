package com.sweet.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sweet.model.User;
import com.sweet.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/29
 */
@Component
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {this.userRepository = userRepository;}

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User " + username + " was not found");
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), getRoles(user.getRoles()));
    }

    private List<GrantedAuthority> getRoles(String roles) {
        return Arrays.stream(roles.split(","))
                .peek(StringUtils::hasText)
                // 给用户加权限时需要给权限加 ROLE_ 前缀， 否则报 type=Forbidden, status=403 错误
                .map(x -> new SimpleGrantedAuthority("ROLE_" + x))
                .collect(Collectors.toList());
    }

}
