package com.sweet.controller;

import com.sweet.service.TestService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    private final TestService testService;

    public TestController(TestService testService) {this.testService = testService;}


    @GetMapping("/lock/{id}")
    public String test(@PathVariable int id) {
        testService.testLock(id);
        return "success";
    }
}
