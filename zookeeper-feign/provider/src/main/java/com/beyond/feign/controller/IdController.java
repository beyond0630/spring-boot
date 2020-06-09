package com.beyond.feign.controller;

import com.beyond.feign.service.IdService;
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
@RequestMapping("/api/id")
public class IdController {

    private final IdService idService;

    public IdController(IdService idService) {this.idService = idService;}

    @GetMapping
    public long nextId() {
        return idService.next();
    }
}
