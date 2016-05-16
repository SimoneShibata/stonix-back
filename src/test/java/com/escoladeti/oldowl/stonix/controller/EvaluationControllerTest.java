package com.escoladeti.oldowl.stonix.controller;


import com.escoladeti.oldowl.stonix.model.Evaluation;
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
public class EvaluationControllerTest extends ControllerTest {
    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<EvaluationController> expected = new ArrayList<>();

        get(EvaluationController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOneEvaluation() throws Exception {
        Date date = new Date(1995,06,12);
        final Evaluation expected = new Evaluation();
        expected.setCreated(date);

        final Evaluation evaluation = new Evaluation();
        evaluation.setCreated(date);

        final MvcResult result = post(EvaluationController.MAPPING, evaluation)
                .andExpect(status().isCreated())
                .andReturn();

        final Evaluation persisted = (Evaluation) parseJson(result, Evaluation.class);
        Assert.assertEquals("Não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneEvaluationAndGet() throws Exception {
        Date date = new Date(1995,06,12);
        final Evaluation evaluationExpected = new Evaluation();
        evaluationExpected.setCreated(date);

        final Evaluation[] expected = new Evaluation[]{
                evaluationExpected
        };

        final Evaluation evaluation = new Evaluation();
        evaluation.setCreated(date);

        post(EvaluationController.MAPPING, evaluation)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(EvaluationController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final Evaluation[] persisted = (Evaluation[]) parseJson(result, Evaluation[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneEvaluationAndDeleteAndGet() throws Exception {
        Date date = new Date(1995,06,12);
        final Evaluation evaluationExpected = new Evaluation();
        evaluationExpected.setCreated(date);

        Evaluation[] expected = new Evaluation[]{
                evaluationExpected
        };

        final Evaluation evaluation = new Evaluation();
        evaluation.setCreated(date);

        post(EvaluationController.MAPPING, evaluation)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(EvaluationController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Evaluation[] persisted = (Evaluation[]) parseJson(result, Evaluation[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        delete(EvaluationController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new Evaluation[]{};

        final MvcResult resultAfterDelete = get(EvaluationController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Evaluation[]) parseJson(resultAfterDelete, Evaluation[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneEvaluationAndPutAndGet() throws Exception {
        Date date = new Date(1995,06,12);
        final Evaluation evaluationExpected = new Evaluation();
        evaluationExpected.setCreated(date);

        Evaluation[] expected = new Evaluation[]{
                evaluationExpected
        };

        final Evaluation evaluation = new Evaluation();
        evaluation.setCreated(date);

        post(EvaluationController.MAPPING, evaluation)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(EvaluationController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        Evaluation[] persisted = (Evaluation[]) parseJson(result, Evaluation[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        final Evaluation evaluation1 = persisted[0];
        evaluation1.setCreated(date);

        put(EvaluationController.MAPPING, evaluation1)
                .andExpect(status().isAccepted());

        final Evaluation evaluation1Expected = new Evaluation();
        evaluation1Expected.setCreated(date);

        expected = new Evaluation[]{
                evaluation1Expected
        };

        final MvcResult resultAfterPut = get(EvaluationController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (Evaluation[]) parseJson(resultAfterPut, Evaluation[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(EvaluationController.MAPPING, "123")
                .andExpect(status().isNotAcceptable());
    }
}
