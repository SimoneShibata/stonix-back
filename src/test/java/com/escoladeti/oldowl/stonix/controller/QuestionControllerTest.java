package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.Question;
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
public class QuestionControllerTest extends ControllerTest {
    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<Question> expected = new ArrayList<>();

        get(QuestionController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOneUser() throws Exception {
        final Question expected = new Question();
        expected.setTitle("Title 1");
        expected.setDescription("Description 1");

        final Question question = new Question();
        question.setTitle("Title 1");
        question.setDescription("Description 1");

        final MvcResult result = post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question persisted = (Question) parseJson(result, Question.class);
        Assert.assertEquals("persisted not equals", question, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndGet() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 2");
        questionExpected.setDescription("Description 2");

        final Question[] expected = new Question[]{
                questionExpected
        };

        final Question question = new Question();
        question.setTitle("Title 2");
        question.setDescription("Description 2");
        post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(QuestionController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Question[] persisted = (Question[]) parseJson(result, Question[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndDeleteAndGet() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 3");
        questionExpected.setDescription("Description 3");

        Question[] expected = new Question[]{
                questionExpected
        };

        final Question question = new Question();
        question.setTitle("Title 3");
        question.setDescription("Description 3");
        post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(QuestionController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Question[] persisted = (Question[]) parseJson(result, Question[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);

        delete(QuestionController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new Question[]{};

        final MvcResult resultAfterDelete = get(QuestionController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Question[]) parseJson(resultAfterDelete, Question[].class);
        Assert.assertArrayEquals("delete not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndPutAndGet() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 4");
        questionExpected.setDescription("Description 4");

        Question[] expected = new Question[]{
                questionExpected
        };

        final Question question = new Question();
        question.setTitle("Title 4");
        question.setDescription("Description 4");
        post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(QuestionController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Question[] persisted = (Question[]) parseJson(result, Question[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);

        final Question question1 = new Question();
        question1.setTitle("Title 4 - putok");
        question1.setDescription("Description 4 - putok");

        put(QuestionController.MAPPING, question1)
                .andExpect(status().isAccepted());

        final Question question1Expected = new Question();
        question1Expected.setTitle("Title 4 - putok");
        question1Expected.setDescription("Description 4 - putok");

        expected = new Question[]{
                question1Expected
        };

        final MvcResult resultAfterPut = get(QuestionController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Question[]) parseJson(resultAfterPut, Question[].class);
        Assert.assertArrayEquals("put not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(QuestionController.MAPPING, "123")
                .andExpect(status().isNotAcceptable());
    }
}
