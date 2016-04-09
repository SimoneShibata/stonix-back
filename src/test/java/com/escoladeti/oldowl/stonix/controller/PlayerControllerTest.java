package com.escoladeti.oldowl.stonix.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tdc on 09/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class PlayerControllerTest extends ConrollerTest {

    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<Object> all = new ArrayList<>();

        get("/api/player")
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(all.toString())));
    }
}
