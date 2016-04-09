package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.Player;
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
 * Created by tdc on 09/04/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
@WebAppConfiguration
@Transactional
public class PlayerControllerTest extends ControllerTest {

    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<Player> expected = new ArrayList<>();

        get(PlayerController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOnePlayer() throws Exception {
        final Player expected = new Player();
        expected.setName("Player 1");

        final Player player = new Player();
        player.setName("Player 1");
        final MvcResult result = post(PlayerController.MAPPING, player)
                .andExpect(status().isCreated())
                .andReturn();

        final Player persisted = (Player) parseJson(result, Player.class);
        Assert.assertEquals("Não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOnePlayerAndGet() throws Exception {
        final Player playerExpected = new Player();
        playerExpected.setName("Player 1");

        final Player[] expected = new Player[]{
                playerExpected
        };

        final Player player = new Player();
        player.setName("Player 1");
        post(PlayerController.MAPPING, player)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(PlayerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Player[] persisted = (Player[]) parseJson(result, Player[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }


    @Test
    @Rollback
    public void postOnePlayerAndDeleteAndGet() throws Exception {
        final Player playerExpected = new Player();
        playerExpected.setName("Player 1");

        Player[] expected = new Player[]{
                playerExpected
        };

        final Player player = new Player();
        player.setName("Player 1");
        post(PlayerController.MAPPING, player)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(PlayerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Player[] persisted = (Player[]) parseJson(result, Player[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        delete(PlayerController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new Player[]{};

        final MvcResult resultAfterDelete = get(PlayerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Player[]) parseJson(resultAfterDelete, Player[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOnePlayerAndPutAndGet() throws Exception {
        final Player playerExpected = new Player();
        playerExpected.setName("Player 1");

        Player[] expected = new Player[]{
                playerExpected
        };

        final Player player = new Player();
        player.setName("Player 1");
        post(PlayerController.MAPPING, player)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(PlayerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Player[] persisted = (Player[]) parseJson(result, Player[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        final Player player1 = persisted[0];
        player1.setName("Player Novo Jogador");

        put(PlayerController.MAPPING, player1)
                .andExpect(status().isAccepted());

        final Player player1Expected = new Player();
        player1Expected.setName("Player Novo Jogador");

        expected = new Player[]{
                player1Expected
        };

        final MvcResult resultAfterPut = get(PlayerController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Player[]) parseJson(resultAfterPut, Player[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(PlayerController.MAPPING, "123")
                .andExpect(status().isNotAcceptable());
    }
}
