package com.escoladeti.oldowl.stonix.unity;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
import org.junit.Assert;
import org.junit.Test;


/**
 * Created by Lucas Cazaroto on 31/05/2016.
 */
public class AnswerUnityTest {

    @Test
    public void testValuesEquals() throws Exception{
        Answer answer = new Answer();
        answer.setDescription("Resposta");
        Assert.assertEquals("Is not equals",answer.getDescription(), "Resposta");
    }

    @Test
    public void testHashCode() throws Exception{
        Answer answer = new Answer();
        Answer expected = new Answer();
        answer.setDescription("Resposta1");
        expected.setDescription("Resposta1");
        Assert.assertEquals("Is not equals", answer, expected);
    }

    @Test
    public void testToString() throws Exception{
        Answer answer = new Answer();
        Answer expected = new Answer();
        answer.setDescription("Resposta");
        expected.setDescription("Resposta");
        Assert.assertEquals("Is not equals", answer.toString(), expected.toString());
    }
}
