package com.beyond.feign.service.impl;

import java.util.concurrent.atomic.AtomicLong;

import com.beyond.feign.service.IdService;
import org.springframework.stereotype.Service;

/**
 * spring-boot
 *
 * @author lucifer
 * @date 2019/10/28
 */
@Service
public class IdServiceImpl implements IdService {

    private static final AtomicLong instance = new AtomicLong(1);

    @Override
    public long next() {
        return instance.getAndAdd(1);
    }
}
