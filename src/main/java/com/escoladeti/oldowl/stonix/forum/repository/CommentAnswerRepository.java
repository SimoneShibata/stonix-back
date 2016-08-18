package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.CommentAnswer;

import java.util.List;

/**
 * Created by Felipe on 30/06/16.
 */
public interface CommentAnswerRepository extends SuperRepository<CommentAnswer> {
    List<CommentAnswer> findByAnswerIdAndDeadIsFalse(String answerId);
}
