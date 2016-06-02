package com.escoladeti.oldowl.stonix.unity;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Description;
import org.springframework.test.annotation.Rollback;

/**
 * Created by Lucas Cazaroto on 31/05/2016.
 */
public class AnswerUnityTest {

    @Test
    @Rollback
    public void testInsertValues() throws Exception{
        Answer answer = new Answer();
        answer.setDescription("Resposta da questão");

    }

    @Test
    @Rollback
    public void testValuesEquals() throws Exception{
        Answer answer = new Answer();
        answer.setDescription("Resposta");
        Assert.assertEquals(answer.getDescription(), "Resposta");
    }

    @Test
    @Rollback
    public void testHashCode() throws Exception{
        Answer answer = new Answer();
        Answer answer1 = new Answer();
        answer.setDescription("Resposta1");
        answer1.setDescription("Resposta1");
        Assert.assertTrue(answer.equals(answer1) && answer1.equals(answer));
        Assert.assertTrue(answer.getDescription().hashCode() == answer1.getDescription().hashCode());
    }
}
