package com.escoladeti.oldowl.stonix.controller;


import com.escoladeti.oldowl.stonix.model.KnowledgeArea;
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
public class KnowledgeAreaControllerTest extends ControllerTest {
    @Test
    @Rollback
    public void testGetAllClean() throws Exception {
        final List<KnowledgeArea> expected = new ArrayList<>();

        get(KnowledgeAreaController.MAPPING)
                .andExpect(status().isOk())
                .andExpect(content().string(equalTo(expected.toString())));
    }

    @Test
    @Rollback
    public void postOneKnowledgeArea() throws Exception {
        final KnowledgeArea expected = new KnowledgeArea();
        expected.setName("Area 1");

        final KnowledgeArea knowledgeArea = new KnowledgeArea();
        knowledgeArea.setName("Area 1");

        final MvcResult result = post(KnowledgeAreaController.MAPPING, knowledgeArea)
                .andExpect(status().isCreated())
                .andReturn();

        final KnowledgeArea persisted = (KnowledgeArea) parseJson(result, KnowledgeArea.class);
        Assert.assertEquals("Não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneKnowledgeAreaAndGet() throws Exception {
        final KnowledgeArea knowledgeAreaExpected = new KnowledgeArea();
        knowledgeAreaExpected.setName("Area 1");

        final KnowledgeArea[] expected = new KnowledgeArea[]{
                knowledgeAreaExpected
        };

        final KnowledgeArea knowledgeArea = new KnowledgeArea();
        knowledgeArea.setName("Area 1");

        post(KnowledgeAreaController.MAPPING, knowledgeArea)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(KnowledgeAreaController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        final KnowledgeArea[] persisted = (KnowledgeArea[]) parseJson(result, KnowledgeArea[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneKnowledgeAreaAndDeleteAndGet() throws Exception {
        final KnowledgeArea knowledgeAreaExpected = new KnowledgeArea();
        knowledgeAreaExpected.setName("Area 1");

        KnowledgeArea[] expected = new KnowledgeArea[]{
                knowledgeAreaExpected
        };

        final KnowledgeArea knowledgeArea = new KnowledgeArea();
        knowledgeArea.setName("Area 1");

        post(KnowledgeAreaController.MAPPING, knowledgeArea)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(KnowledgeAreaController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        KnowledgeArea[] persisted = (KnowledgeArea[]) parseJson(result, KnowledgeArea[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        delete(KnowledgeAreaController.MAPPING, persisted[0].getId())
                .andExpect(status().isOk());

        expected = new KnowledgeArea[]{};

        final MvcResult resultAfterDelete = get(KnowledgeAreaController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (KnowledgeArea[]) parseJson(resultAfterDelete, KnowledgeArea[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void postOneKnowledgeAreaAndPutAndGet() throws Exception {
        final KnowledgeArea knowledgeAreaExpected = new KnowledgeArea();
        knowledgeAreaExpected.setName("Area 1");

        KnowledgeArea[] expected = new KnowledgeArea[]{
                knowledgeAreaExpected
        };

        final KnowledgeArea knowledgeArea = new KnowledgeArea();
        knowledgeArea.setName("Area 1");

        post(KnowledgeAreaController.MAPPING, knowledgeArea)
                .andExpect(status().isCreated())
                .andReturn();

        final MvcResult result = get(KnowledgeAreaController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        KnowledgeArea[] persisted = (KnowledgeArea[]) parseJson(result, KnowledgeArea[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);

        final KnowledgeArea knowledgeArea1 = persisted[0];
        knowledgeArea1.setName("New Area");

        put(KnowledgeAreaController.MAPPING, knowledgeArea1)
                .andExpect(status().isAccepted());

        final KnowledgeArea knowledgeArea1Expected = new KnowledgeArea();
        knowledgeArea1Expected.setName("New Area");

        expected = new KnowledgeArea[]{
                knowledgeArea1Expected
        };

        final MvcResult resultAfterPut = get(KnowledgeAreaController.MAPPING)
                .andExpect(status().isOk())
                .andReturn();

        persisted = (KnowledgeArea[]) parseJson(resultAfterPut, KnowledgeArea[].class);
        Assert.assertArrayEquals("Resultado não é igual", expected, persisted);
    }

    @Test
    @Rollback
    public void testTryToDeleteInvalidId() throws Exception {
        delete(KnowledgeAreaController.MAPPING, "123")
                .andExpect(status().isNotAcceptable());
    }
}
