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
    public void testValuesEquals() throws Exception{
        Answer answer = new Answer();
        answer.setDescription("Resposta");
        Assert.assertEquals(answer.getDescription(), "Resposta");
    }

    @Test
    @Rollback
    public void testHashCode() throws Exception{
        Answer answer = new Answer();
        Answer expected = new Answer();
        answer.setDescription("Resposta1");
        expected.setDescription("Resposta1");
        Assert.assertEquals("Is not equals", answer, expected);
    }
}
