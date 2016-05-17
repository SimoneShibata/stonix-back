package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.User;
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
 * Created by Felipe on 09/04/2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class UserControllerTest extends ControllerTest {
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
        Date data = new Date(1995,06,12);
        final User expected = new User();
        expected.setName("User 1");
        expected.setBirth(data);
        expected.setNick("FuckYeah");
        expected.setEmail("user@user.com");
        expected.setPhone("99521921");

        final User user = new User();
        user.setName("User 1");
        user.setBirth(data);
        user.setNick("FuckYeah");
        user.setEmail("user@user.com");
        user.setPhone("99521921");

        final MvcResult result = post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        final User persisted = (User) parseJson(result, User.class);
        Assert.assertEquals("Não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndGet() throws Exception {
        Date data = new Date(1995,06,12);
        final User userExpected = new User();
        userExpected.setName("User 1");
        userExpected.setBirth(data);
        userExpected.setNick("FuckYeah");
        userExpected.setEmail("user@user.com");
        userExpected.setPhone("99521921");

        final User[] expected = new User[]{
                userExpected
        };

        final User user = new User();
        user.setName("User 1");
        user.setBirth(data);
        user.setNick("FuckYeah");
        user.setEmail("user@user.com");
        user.setPhone("99521921");
        post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final User[] persisted = (User[]) parseJson(result, User[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndDeleteAndGet() throws Exception {
        Date data = new Date(1995,06,12);
        final User userExpected = new User();
        userExpected.setName("User 1");
        userExpected.setBirth(data);
        userExpected.setNick("FuckYeah");
        userExpected.setEmail("user@user.com");
        userExpected.setPhone("99521921");

        User[] expected = new User[]{
                userExpected
        };

        final User user = new User();
        user.setName("User 1");
        user.setBirth(data);
        user.setNick("FuckYeah");
        user.setEmail("user@user.com");
        user.setPhone("99521921");
        post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        User[] persisted = (User[]) parseJson(result, User[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        delete(UserController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new User[]{};

        final MvcResult resultAfterDelete = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (User[]) parseJson(resultAfterDelete, User[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneUserAndPutAndGet() throws Exception {
        Date data = new Date(1995,06,12);
        final User userExpected = new User();
        userExpected.setName("User 1");
        userExpected.setBirth(data);
        userExpected.setNick("FuckYeah");
        userExpected.setEmail("user@user.com");
        userExpected.setPhone("99521921");
        User[] expected = new User[]{
                userExpected
        };

        final User user = new User();
        user.setName("User 1");
        user.setBirth(data);
        user.setNick("FuckYeah");
        user.setEmail("user@user.com");
        user.setPhone("99521921");
        post(UserController.MAPPING, user)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        User[] persisted = (User[]) parseJson(result, User[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        final User user1 = persisted[0];
        user1.setName("New User");
        user1.setBirth(data);
        user1.setNick("NotForgive");
        user1.setEmail("new@user.com");
        user1.setPhone("naotembeagle");

        put(UserController.MAPPING, user1)
                .andExpect(status().isAccepted());

        final User user1Expected = new User();
        user1Expected.setName("New User");
        user1Expected.setBirth(data);
        user1Expected.setNick("NotForgive");
        user1Expected.setEmail("new@user.com");
        user1Expected.setPhone("naotembeagle");

        expected = new User[]{
                user1Expected
        };

        final MvcResult resultAfterPut = get(UserController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (User[]) parseJson(resultAfterPut, User[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(UserController.MAPPING, "123")
                .andExpect(status().isNotAcceptable());
    }
}
