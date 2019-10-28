package com.sweet.controller;

import com.sweet.client.service.ClientIdService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/28
 */
@RestController
@RequestMapping("/id")
public class IdController {

    private final ClientIdService clientIdService;

    public IdController(ClientIdService clientIdService) {this.clientIdService = clientIdService;}

    @GetMapping
    public long get() {
        return clientIdService.nextId();
    }
}
