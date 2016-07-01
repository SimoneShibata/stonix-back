package com.escoladeti.oldowl.stonix.unity;

import com.escoladeti.oldowl.stonix.enums.Status;
import com.escoladeti.oldowl.stonix.forum.model.Comment;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Lucas Cazaroto on 10/06/2016.
 */
public class CommentUnitTest {

    @Test
    public void testValuesEquals()throws Exception{
        Comment comment = new Comment();
        comment.setDescription("Comentario");
        comment.setStatus(Status.ACTIVE);
        Assert.assertEquals("Is not Equals", comment.getDescription(), "Comentario");
        Assert.assertEquals("Is not Equals", comment.getStatus(), Status.ACTIVE);
    }

    @Test
    public void testHashCode()throws Exception{
        Comment comment = new Comment();
        Comment expected = new Comment();
        comment.setDescription("Comentario1");
        expected.setDescription("Comentario1");
        comment.setStatus(Status.ACTIVE);
        expected.setStatus(Status.ACTIVE);
        Assert.assertEquals("Is not Equals", comment.getDescription(), expected.getDescription());
        Assert.assertEquals("Is not Equals", comment.getStatus(), comment.getStatus());
    }

    @Test
    public void testToString() throws Exception{
        Comment comment = new Comment();
        Comment expected = new Comment();
        comment.setDescription("Comentario");
        expected.setDescription("Comentario");
        comment.setStatus(Status.ACTIVE);
        expected.setStatus(Status.ACTIVE);
        Assert.assertEquals("Is not Equals", comment.toString(), expected.toString());
    }
}
