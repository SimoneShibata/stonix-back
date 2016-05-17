package com.escoladeti.oldowl.stonix.controller;


import com.escoladeti.oldowl.stonix.model.Password;
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
public class PasswordControllerTest extends ControllerTest {
    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<Password> expected = new ArrayList<>();

        get(PasswordController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOnePassword() throws Exception {
        final Password expected = new Password();
        expected.setValue("bazinga");

        final Password password = new Password();
        password.setValue("bazinga");

        final MvcResult result = post(PasswordController.MAPPING, password)
                .andExpect(status().isCreated())
                .andReturn();

        final Password persisted = (Password) parseJson(result, Password.class);
        Assert.assertEquals("Não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOnePasswordAndGet() throws Exception {
        final Password passwordExpected = new Password();
        passwordExpected.setValue("bazinga");

        final Password[] expected = new Password[]{
                passwordExpected
        };

        final Password password = new Password();
        password.setValue("bazinga");

        post(PasswordController.MAPPING, password)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(PasswordController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Password[] persisted = (Password[]) parseJson(result, Password[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOnePasswordAndDeleteAndGet() throws Exception {
        final Password passwordExpected = new Password();
        passwordExpected.setValue("bazinga");

        Password[] expected = new Password[]{
                passwordExpected
        };

        final Password password = new Password();
        password.setValue("bazinga");

        post(PasswordController.MAPPING, password)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(PasswordController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Password[] persisted = (Password[]) parseJson(result, Password[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        delete(PasswordController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new Password[]{};

        final MvcResult resultAfterDelete = get(PasswordController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Password[]) parseJson(resultAfterDelete, Password[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOnePasswordAndPutAndGet() throws Exception {
        final Password passwordExpected = new Password();
        passwordExpected.setValue("bazinga");

        Password[] expected = new Password[]{
                passwordExpected
        };

        final Password password = new Password();
        password.setValue("bazinga");

        post(PasswordController.MAPPING, password)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(PasswordController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Password[] persisted = (Password[]) parseJson(result, Password[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        final Password password1 = persisted[0];
        password1.setValue("New Bazinga");

        put(PasswordController.MAPPING, password1)
                .andExpect(status().isAccepted());

        final Password password1Expected = new Password();
        password1Expected.setValue("New Bazinga");

        expected = new Password[]{
                password1Expected
        };

        final MvcResult resultAfterPut = get(PasswordController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Password[]) parseJson(resultAfterPut, Password[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(PasswordController.MAPPING, "123")
                .andExpect(status().isNotAcceptable());
    }
}
