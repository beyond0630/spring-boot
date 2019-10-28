package com.sweet.client.service;

import com.sweet.client.fallback.IdServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/28
 */
@FeignClient(value = "provider", fallbackFactory = IdServiceFallback.class)
public interface ClientIdService {

    @GetMapping("/api/id")
    long nextId();
}
