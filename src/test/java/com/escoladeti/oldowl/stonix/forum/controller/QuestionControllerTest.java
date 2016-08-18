package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
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
public class QuestionControllerTest extends SuperControllerTest {

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
    public void postOneQuestion() throws Exception {
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

//    @Test
//    @Rollback
//    public void postOneQuestionAndPostOneAnswer() throws Exception {
//
//        final Question question = new Question();
//        question.setTitle("Title");
//        question.setDescription("Description");
//
//        post(QuestionController.MAPPING, question)
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        Answer answer = new Answer();
//        answer.setDescription("Description");
//        answer.setQuestion(question);
//
//        List<Answer> answerList = new ArrayList();
//
//        answerList.add(answer);
//        question.setAnswerList(answerList);
//
//        put(QuestionController.MAPPING + "/" + question.getId() + "/answers", answer)
//                .andExpect(status().isAccepted());
//    }

//    @Test
//    @Rollback
//    public void postOneQuestionAndPostOneComment() throws Exception {
//
//        final Question question = new Question();
//        question.setTitle("Title");
//        question.setDescription("Description");
//
//        post(QuestionController.MAPPING, question)
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        CommentQuestion commentQuestion = new CommentQuestion();
//        commentQuestion.setDescription("Description");
//
//        put(QuestionController.MAPPING + "/" + question.getId() + "/comment", commentQuestion)
//                .andExpect(status().isAccepted());
//    }

    @Test
    @Rollback
    public void postOneQuestionAndGetOneQuestion() throws Exception {
        final Question question = new Question();
        question.setTitle("Title");
        question.setDescription("Description");
        final MvcResult result = post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question persisted = (Question) parseJson(result, Question.class);

        final MvcResult search = get(QuestionController.MAPPING + "/" + question.getId())
                .andExpect(status().isOk())
                .andReturn();

        final Question find = (Question) parseJson(search, Question.class);

        Assert.assertEquals("is not equals", persisted, find);
    }

    @Test
    @Rollback
    public void postOneQuestionAndGet() throws Exception {

        final Question question = new Question();
        question.setTitle("Title 2");
        question.setDescription("Description 2");
        post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        final Question[] expected = new Question[]{
                question
        };

        final MvcResult result = get(QuestionController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Question[] persisted = (Question[]) parseJson(result, Question[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneQuestionAndDeleteAndGet() throws Exception {
        final Question question = new Question();
        question.setTitle("Title 3");
        question.setDescription("Description 3");

        Question[] expected = new Question[]{
                question
        };
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
    public void postOneQuestionAndPutAndGet() throws Exception {

        final Question question = new Question();
        question.setTitle("Title 4");
        question.setDescription("Description 4");
        post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        Question[] expected = new Question[]{
                question
        };

        final MvcResult result = get(QuestionController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Question[] persisted = (Question[]) parseJson(result, Question[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);


        question.setTitle("Title 4 - putok");
        question.setDescription("Description 4 - putok");

        put(QuestionController.MAPPING, question)
                .andExpect(status().isAccepted());


        expected = new Question[]{
                question
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
                .andExpect(status().isNotFound());
    }

    @Test
    @Rollback
    public void testTryToPutInvalidArguments() throws Exception {
        Question question = new Question();
        question.setTitle("Title");
        question.setDescription("Description");

        post(QuestionController.MAPPING, question)
                .andExpect(status().isCreated())
                .andReturn();

        question.setTitle("");
        question.setDescription("");

        put(QuestionController.MAPPING, question)
                .andExpect(status().isNotAcceptable());
    }
}