package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Comment;
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
 * Created by Felipe on 30/05/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class CommentControllerTest extends SuperControllerTest {

    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<Comment> expected = new ArrayList<>();

        get(CommentController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOneComment() throws Exception {
        final Comment expected = new Comment();
        expected.setDescription("Description 1");

        final Comment comment = new Comment();
        comment.setDescription("Description 1");

        final MvcResult result = post(CommentController.MAPPING, comment)
                .andExpect(status().isCreated())
                .andReturn();

        final Comment persisted = (Comment) parseJson(result, Comment.class);
        Assert.assertEquals("persisted not equals", comment, persisted);
    }

    @Test
    @Rollback
    public void postOneCommentAndGetOneComment() throws Exception {
        final Comment comment = new Comment();
        comment.setDescription("Description");
        final MvcResult result = post(CommentController.MAPPING, comment)
                .andExpect(status().isCreated())
                .andReturn();

        final Comment persisted = (Comment) parseJson(result, Comment.class);

        final MvcResult search = get(CommentController.MAPPING + "/" + comment.getId())
                .andExpect(status().isOk())
                .andReturn();

        final Comment find = (Comment) parseJson(search,Comment.class);

        Assert.assertEquals("is not equals", persisted, find);
    }

    @Test
    @Rollback
    public void postOneCommentAndGet() throws Exception {

        final Comment comment = new Comment();
        comment.setDescription("Description 2");
        post(CommentController.MAPPING, comment)
                .andExpect(status().isCreated())
                .andReturn();

        final Comment[] expected = new Comment[]{
                comment
        };

        final MvcResult result = get(CommentController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Comment[] persisted = (Comment[]) parseJson(result, Comment[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneCommentAndDeleteAndGet() throws Exception {
        final Comment comment = new Comment();
        comment.setDescription("Description 3");

        Comment[] expected = new Comment[]{
                comment
        };
        post(CommentController.MAPPING, comment)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(CommentController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Comment[] persisted = (Comment[]) parseJson(result, Comment[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);

        delete(CommentController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new Comment[]{};

        final MvcResult resultAfterDelete = get(CommentController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Comment[]) parseJson(resultAfterDelete, Comment[].class);
        Assert.assertArrayEquals("delete not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneCommentAndPutAndGet() throws Exception {

        final Comment comment = new Comment();
        comment.setDescription("Description 4");
        post(CommentController.MAPPING, comment)
                .andExpect(status().isCreated())
                .andReturn();

        Comment[] expected = new Comment[]{
                comment
        };

        final MvcResult result = get(CommentController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Comment[] persisted = (Comment[]) parseJson(result, Comment[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);


        comment.setDescription("Description 4 - putok");

        put(CommentController.MAPPING, comment)
                .andExpect(status().isAccepted());


        expected = new Comment[]{
                comment
        };

        final MvcResult resultAfterPut = get(CommentController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Comment[]) parseJson(resultAfterPut, Comment[].class);
        Assert.assertArrayEquals("put not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(CommentController.MAPPING, "123")
                .andExpect(status().isNotFound());
    }

    @Test
    @Rollback
    public void testTryToPutInvalidArguments() throws Exception{
        Comment comment = new Comment();
        comment.setDescription("Description");

        post(CommentController.MAPPING, comment)
                .andExpect(status().isCreated())
                .andReturn();

        comment.setDescription("");

        put(CommentController.MAPPING, comment)
                .andExpect(status().isNotAcceptable());
    }
}