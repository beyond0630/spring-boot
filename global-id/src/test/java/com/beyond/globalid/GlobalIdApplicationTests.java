package com.beyond.globalid;

import com.beyond.globalid.generator.IdGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GlobalIdApplicationTests {


    @Autowired
    private IdGenerator idGenerator;

    @Test
    void generate() {
        for (int i = 0; i < 100; i++) {
            System.out.println(idGenerator.generate());
        }
    }

}
