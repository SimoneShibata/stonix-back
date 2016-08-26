package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.User;
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
import java.util.Date;
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
public class UserControllerTest extends SuperControllerTest {

    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<User> expected = new ArrayList<>();

        get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOneUser() throws Exception {
        final User expected = new User();
        Date date = new Date(2016,12,06);
        expected.setName("Name");
        expected.setBirth(date);

        final User user = new User();

        user.setName("Name");
        user.setBirth(date);

        final MvcResult result = post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        final User persisted = (User) parseJson(result, User.class);
        Assert.assertEquals("persisted not equals", user, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndGetOneUser() throws Exception {
        Date date = new Date(2016,12,06);
        final User user = new User();
        user.setName("Name");
        user.setBirth(date);

        final MvcResult result = post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        final User persisted = (User) parseJson(result, User.class);

        final MvcResult search = get(UserController.MAPPING + "/" + user.getId())
                .andExpect(status().isOk())
                .andReturn();

        final User find = (User) parseJson(search,User.class);

        Assert.assertEquals("is not equals", persisted, find);
    }

    @Test
    @Rollback
    public void postOneUserAndGet() throws Exception {
        Date date = new Date(2016,12,06);
        final User user = new User();
        user.setName("Name");
        user.setBirth(date);

        post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        final User[] expected = new User[]{
                user
        };

        final MvcResult result = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final User[] persisted = (User[]) parseJson(result, User[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndDeleteAndGet() throws Exception {
        Date date = new Date(2016,12,06);
        final User user = new User();
        user.setName("Name");
        user.setBirth(date);

        User[] expected = new User[]{
                user
        };
        post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        User[] persisted = (User[]) parseJson(result, User[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);

        delete(UserController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new User[]{};

        final MvcResult resultAfterDelete = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (User[]) parseJson(resultAfterDelete, User[].class);
        Assert.assertArrayEquals("delete not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndPutAndGet() throws Exception {
        Date date = new Date(2016,12,06);
        final User user = new User();
        user.setName("Name");
        user.setBirth(date);

        post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        User[] expected = new User[]{
                user
        };

        final MvcResult result = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        User[] persisted = (User[]) parseJson(result, User[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);


        user.setName("Name put");
        user.setBirth(date);

        put(UserController.MAPPING, user)
                .andExpect(status().isAccepted());


        expected = new User[]{
                user
        };

        final MvcResult resultAfterPut = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (User[]) parseJson(resultAfterPut, User[].class);
        Assert.assertArrayEquals("put not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(UserController.MAPPING, "123")
                .andExpect(status().isNotFound());
    }

    @Test
    @Rollback
    public void testTryToPutInvalidArguments() throws Exception{
        Date date = new Date(2016,12,06);
        User user = new User();
        user.setName("Name");
        user.setBirth(date);

        post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();
        Date date2 = new Date();
        user.setName("");
        user.setBirth(date2);

        put(UserController.MAPPING, user)
                .andExpect(status().isNotAcceptable());
    }
}