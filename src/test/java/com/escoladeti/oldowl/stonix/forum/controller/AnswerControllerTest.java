package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
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
 * Created by Felipe on 28/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class AnswerControllerTest extends SuperControllerTest {

    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<Answer> expected = new ArrayList<>();

        get(AnswerController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOneAnswer() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 1");
        questionExpected.setDescription("Description 1");

        final Question question = new Question();
        question.setTitle("Title 1");
        question.setDescription("Description 1");

        final MvcResult questionResult = post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question questionPersisted = (Question) parseJson(questionResult, Question.class);
        Assert.assertEquals("persisted not equals", question, questionPersisted);

        final Answer expected = new Answer();
        expected.setDescription("Description 1");

        final Answer answer = new Answer();
        answer.setDescription("Description 1");

        answer.setQuestion(question);

        final MvcResult result = post(AnswerController.MAPPING, answer)
                .andExpect(status().isCreated())
                .andReturn();

        final Answer persisted = (Answer) parseJson(result, Answer.class);
        Assert.assertEquals("persisted not equals", answer, persisted);
    }

    @Test
    @Rollback
    public void postOneAnswerAndGetOneAnswer() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 1");
        questionExpected.setDescription("Description 1");

        final Question question = new Question();
        question.setTitle("Title 1");
        question.setDescription("Description 1");

        final MvcResult questionResult = post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question questionPersisted = (Question) parseJson(questionResult, Question.class);
        Assert.assertEquals("persisted not equals", question, questionPersisted);

        final Answer answer = new Answer();
        answer.setDescription("Description");
        answer.setQuestion(question);
        final MvcResult result = post(AnswerController.MAPPING, answer)
                .andExpect(status().isCreated())
                .andReturn();

        final Answer persisted = (Answer) parseJson(result, Answer.class);

        final MvcResult search = get(AnswerController.MAPPING + "/" + answer.getId())
                .andExpect(status().isOk())
                .andReturn();

        final Answer find = (Answer) parseJson(search, Answer.class);

        Assert.assertEquals("is not equals", persisted, find);
    }

    @Test
    @Rollback
    public void postOneAnswerAndGet() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 1");
        questionExpected.setDescription("Description 1");

        final Question question = new Question();
        question.setTitle("Title 1");
        question.setDescription("Description 1");

        final MvcResult questionResult = post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question questionPersisted = (Question) parseJson(questionResult, Question.class);
        Assert.assertEquals("persisted not equals", question, questionPersisted);

        final Answer answer = new Answer();
        answer.setDescription("Description 2");
        answer.setQuestion(question);
        post(AnswerController.MAPPING, answer)
                .andExpect(status().isCreated())
                .andReturn();

        final Answer[] expected = new Answer[]{
                answer
        };

        final MvcResult result = get(AnswerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Answer[] persisted = (Answer[]) parseJson(result, Answer[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneAnswerAndDeleteAndGet() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 1");
        questionExpected.setDescription("Description 1");

        final Question question = new Question();
        question.setTitle("Title 1");
        question.setDescription("Description 1");

        final MvcResult questionResult = post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question questionPersisted = (Question) parseJson(questionResult, Question.class);
        Assert.assertEquals("persisted not equals", question, questionPersisted);

        final Answer answer = new Answer();
        answer.setDescription("Description 3");
        answer.setQuestion(question);

        Answer[] expected = new Answer[]{
                answer
        };
        post(AnswerController.MAPPING, answer)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(AnswerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Answer[] persisted = (Answer[]) parseJson(result, Answer[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);

        delete(AnswerController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new Answer[]{};

        final MvcResult resultAfterDelete = get(AnswerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Answer[]) parseJson(resultAfterDelete, Answer[].class);
        Assert.assertArrayEquals("delete not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneAnswerAndPutAndGet() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 1");
        questionExpected.setDescription("Description 1");

        final Question question = new Question();
        question.setTitle("Title 1");
        question.setDescription("Description 1");

        final MvcResult questionResult = post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question questionPersisted = (Question) parseJson(questionResult, Question.class);
        Assert.assertEquals("persisted not equals", question, questionPersisted);

        final Answer answer = new Answer();
        answer.setDescription("Description 4");
        answer.setQuestion(question);
        post(AnswerController.MAPPING, answer)
                .andExpect(status().isCreated())
                .andReturn();

        Answer[] expected = new Answer[]{
                answer
        };

        final MvcResult result = get(AnswerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Answer[] persisted = (Answer[]) parseJson(result, Answer[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);


        answer.setDescription("Description 4 - putok");

        put(AnswerController.MAPPING, answer)
                .andExpect(status().isAccepted());


        expected = new Answer[]{
                answer
        };

        final MvcResult resultAfterPut = get(AnswerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Answer[]) parseJson(resultAfterPut, Answer[].class);
        Assert.assertArrayEquals("put not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(AnswerController.MAPPING, "123")
                .andExpect(status().isNotFound());
    }

    @Test
    @Rollback
    public void testTryToPutInvalidArguments() throws Exception {
        final Question questionExpected = new Question();
        questionExpected.setTitle("Title 1");
        questionExpected.setDescription("Description 1");

        final Question question = new Question();
        question.setTitle("Title 1");
        question.setDescription("Description 1");

        final MvcResult questionResult = post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question questionPersisted = (Question) parseJson(questionResult, Question.class);
        Assert.assertEquals("persisted not equals", question, questionPersisted);

        Answer answer = new Answer();
        answer.setDescription("Description");
        answer.setQuestion(question);

        post(AnswerController.MAPPING, answer)
                .andExpect(status().isCreated())
                .andReturn();

        answer.setDescription("");

        put(AnswerController.MAPPING, answer)
                .andExpect(status().isNotAcceptable());
    }
}