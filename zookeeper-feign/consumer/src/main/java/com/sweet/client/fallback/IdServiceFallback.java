package com.sweet.client.fallback;

import com.sweet.client.service.ClientIdService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/28
 */
@Component
public class IdServiceFallback implements FallbackFactory<ClientIdService> {

    @Override
    public ClientIdService create(Throwable throwable) {
        return () -> -1;
    }
}
