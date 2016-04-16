package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.Comment;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import javax.transaction.Transactional;
import java.nio.charset.Charset;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by tdc on 15/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class CommentControllerTest extends ControllerTest {

    @Test
    @Rollback
    public void getAllClean() throws Exception {
        final Comment[] expected = new Comment[]{};

        final MvcResult result = get(CommentController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Comment[] persisted = (Comment[]) parseJson(result, Comment[].class);
        Assert.assertArrayEquals("Objetos não são iguais", expected, persisted);
    }

    @Test
    @Rollback
    public void postOne() throws Exception {
        final Comment expected = new Comment();
        expected.setDescription("Descrição do Comentario");

        final Comment comment = new Comment();
        comment.setDescription("Descrição do Comentario");
        final MvcResult result = post(CommentController.MAPPING, comment)
                .andExpect(status().isCreated())
                .andReturn();

        final Comment persisted = (Comment) parseJson(result, Comment.class);
        Assert.assertEquals("Objetos não são iguais", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneAndGet() throws Exception {
        final Comment expected1 = new Comment();
        expected1.setDescription("Descrição do Objeto 1");
        final Comment[] expected = new Comment[]{
                expected1
        };

        final Comment comment = new Comment();
        comment.setDescription("Descrição do Objeto 1");
        final MvcResult result = post(CommentController.MAPPING, comment)
                .andExpect(status().isCreated())
                .andReturn();

        final Comment persisted = (Comment) parseJson(result, Comment.class);
        Assert.assertEquals("Objetos não são iguais", expected1, persisted);

        final MvcResult resultGet = get(CommentController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();
        final Comment[] persistedAll = (Comment[]) parseJson(resultGet, Comment[].class);
        Assert.assertArrayEquals("Objetos não são iguais", expected, persistedAll);
    }

    @Test(expected = JsonMappingException.class)
    @Rollback
    public void postOneAndFail() throws Exception {
        final MockHttpServletRequestBuilder builder = MockMvcRequestBuilders.post(CommentController.MAPPING);
        builder.content("{\"description\":\"Descrição do Comentario\",\"created\":\"DATA_INVALIDA\"}");
        builder.contentType(new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(),
                Charset.forName("utf8")));

        final MvcResult result = mvc.perform(builder)
                .andReturn();

        final Comment persisted = (Comment) parseJson(result, Comment.class);
        Assert.assertEquals("Objetos não são iguais", new Comment(), persisted);
    }
}
