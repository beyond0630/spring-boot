package com.sweet.service.impl;

import com.sweet.annotation.Lock;
import com.sweet.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class TestServiceImpl implements TestService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestServiceImpl.class);

    @Override
    @Lock(key = "'test'")
    public void testLock(int id) {

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LOGGER.debug("working...");
    }
}
