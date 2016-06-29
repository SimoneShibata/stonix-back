package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.CommentQuestion;
import com.escoladeti.oldowl.stonix.forum.model.Question;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Felipe on 09/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class CommentQuestionControllerTest extends SuperControllerTest {

    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<CommentQuestion> expected = new ArrayList<>();

        get(CommentQuestionController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(CommentQuestionController.MAPPING, "123")
                .andExpect(status().isNotFound());
    }
}