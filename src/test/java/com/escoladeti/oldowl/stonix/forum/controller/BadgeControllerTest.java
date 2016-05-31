package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Badge;
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
public class BadgeControllerTest extends SuperControllerTest {

    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<Badge> expected = new ArrayList<>();

        get(BadgeController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOneBadge() throws Exception {
        final Badge expected = new Badge();
        expected.setDescription("Description 1");
        expected.setImage("image");
        expected.setName("name");

        final Badge badge = new Badge();
        badge.setDescription("Description 1");
        badge.setImage("image");
        badge.setName("name");
        final MvcResult result = post(BadgeController.MAPPING, badge)
                .andExpect(status().isCreated())
                .andReturn();

        final Badge persisted = (Badge) parseJson(result, Badge.class);
        Assert.assertEquals("persisted not equals", badge, persisted);
    }

    @Test
    @Rollback
    public void postOneBadgeAndGetOneBadge() throws Exception {
        final Badge badge = new Badge();
        badge.setDescription("Description");
        badge.setName("name");
        badge.setImage("image");

        final MvcResult result = post(BadgeController.MAPPING, badge)
                .andExpect(status().isCreated())
                .andReturn();

        final Badge persisted = (Badge) parseJson(result, Badge.class);

        final MvcResult search = get(BadgeController.MAPPING + "/" + badge.getId())
                .andExpect(status().isOk())
                .andReturn();

        final Badge find = (Badge) parseJson(search,Badge.class);

        Assert.assertEquals("is not equals", persisted, find);
    }

    @Test
    @Rollback
    public void postOneBadgeAndGet() throws Exception {

        final Badge badge = new Badge();
        badge.setDescription("Description 2");
        badge.setName("name 2");
        badge.setImage("image 2");

        post(BadgeController.MAPPING, badge)
                .andExpect(status().isCreated())
                .andReturn();

        final Badge[] expected = new Badge[]{
                badge
        };

        final MvcResult result = get(BadgeController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Badge[] persisted = (Badge[]) parseJson(result, Badge[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneBadgeAndDeleteAndGet() throws Exception {
        final Badge badge = new Badge();
        badge.setDescription("Description 3");
        badge.setImage("image 3");
        badge.setName("name 3");

        Badge[] expected = new Badge[]{
                badge
        };
        post(BadgeController.MAPPING, badge)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(BadgeController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Badge[] persisted = (Badge[]) parseJson(result, Badge[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);

        delete(BadgeController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new Badge[]{};

        final MvcResult resultAfterDelete = get(BadgeController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Badge[]) parseJson(resultAfterDelete, Badge[].class);
        Assert.assertArrayEquals("delete not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneBadgeAndPutAndGet() throws Exception {

        final Badge badge = new Badge();
        badge.setDescription("Description 4");
        badge.setName("name 4");
        badge.setImage("image 4");

        post(BadgeController.MAPPING, badge)
                .andExpect(status().isCreated())
                .andReturn();

        Badge[] expected = new Badge[]{
                badge
        };

        final MvcResult result = get(BadgeController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Badge[] persisted = (Badge[]) parseJson(result, Badge[].class);
        Assert.assertArrayEquals("get is not equals", expected, persisted);


        badge.setDescription("Description 4 - putok");
        badge.setImage("image 4 - putok");
        badge.setName("name 4 - putok");

        put(BadgeController.MAPPING, badge)
                .andExpect(status().isAccepted());


        expected = new Badge[]{
                badge
        };

        final MvcResult resultAfterPut = get(BadgeController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Badge[]) parseJson(resultAfterPut, Badge[].class);
        Assert.assertArrayEquals("put not equals", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(BadgeController.MAPPING, "123")
                .andExpect(status().isNotFound());
    }

    @Test
    @Rollback
    public void testTryToPutInvalidArguments() throws Exception{
        Badge badge = new Badge();
        badge.setDescription("Description");
        badge.setName("name");
        badge.setImage("image");

        post(BadgeController.MAPPING, badge)
                .andExpect(status().isCreated())
                .andReturn();

        badge.setDescription("");
        badge.setImage("");
        badge.setName("");

        put(BadgeController.MAPPING, badge)
                .andExpect(status().isNotAcceptable());
    }
}