package com.escoladeti.oldowl.stonix.unity;

import com.escoladeti.oldowl.stonix.forum.model.Badge;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lucas Cazaroto on 09/06/2016.
 */
public class BadgeUnitTest {

    @Test
    public void testValuesEquals() throws Exception{
        Badge badge = new Badge();
        badge.setDescription("Cracha");
        badge.setName("Nome");
        badge.setImage("Imagem");
        Assert.assertEquals("Is not equals", badge.getDescription(), "Cracha");
        Assert.assertEquals("Is not equals", badge.getName(), "Nome");
        Assert.assertEquals("Is not equals", badge.getImage(), "Imagem");
    }

    @Test
    public void testHashCode() throws Exception{
        Badge badge = new Badge();
        Badge expected = new Badge();
        badge.setDescription("Cracha1");
        expected.setDescription("Cracha1");
        badge.setName("Name1");
        expected.setName("Name1");
        badge.setImage("Imagem1");
        expected.setImage("Imagem1");
        Assert.assertEquals("Is not equals", badge.getDescription(), expected.getDescription());
        Assert.assertEquals("Is not equals", badge.getName(), expected.getName());
        Assert.assertEquals("Is not equals", badge.getImage(), expected.getImage());
    }

    @Test
    public void testToString() throws Exception{
        Badge badge = new Badge();
        Badge expected = new Badge();
        badge.setDescription("Cracha");
        expected.setDescription("Cracha");
        badge.setName("Nome");
        expected.setName("Nome");
        badge.setImage("Imagem");
        expected.setImage("Imagem");
        Assert.assertEquals("Is not equals", badge.toString(), expected.toString());
        Assert.assertEquals("Is not equals", badge.toString(), expected.toString());
        Assert.assertEquals("Is not equals", badge.toString(), expected.toString());
    }
}
