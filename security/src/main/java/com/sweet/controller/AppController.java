package com.sweet.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/29
 */
@RestController
public class AppController {

    @RequestMapping("/hello")
    public String home() {
        return "Hello ,spring security!";
    }
}