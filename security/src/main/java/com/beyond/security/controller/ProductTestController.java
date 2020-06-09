package com.beyond.security.controller;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductTestController {

    @RequestMapping("/info")
    public String productInfo() {
        String currentUser = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            currentUser = ((UserDetails) principal).getUsername();
        } else {
            currentUser = principal.toString();
        }
        return " some product info,currentUser is: " + currentUser;
    }
}