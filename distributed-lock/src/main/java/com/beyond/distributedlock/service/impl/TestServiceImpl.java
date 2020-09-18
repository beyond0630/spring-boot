package com.beyond.distributedlock.service.impl;

import com.beyond.distributedlock.annotation.Lock;
import com.beyond.distributedlock.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    @Lock(key = "#id", timeout = 1000)
    public void testLock(int id) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.debug("working...");
    }
}
