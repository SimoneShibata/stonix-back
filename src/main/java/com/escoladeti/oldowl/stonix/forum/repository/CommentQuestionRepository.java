package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.CommentQuestion;

import java.util.List;

/**
 * Created by tdc on 09/04/16.
 */
public interface CommentQuestionRepository extends SuperRepository<CommentQuestion> {
    List<CommentQuestion> findByQuestionIdAndDeadIsFalse(String questionId);
}
