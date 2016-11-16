package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.FlagQuestion;

import java.util.List;

/**
 * Created by Felipe on 03/11/2016.
 */
public interface FlagQuestionRepository extends SuperRepository<FlagQuestion> {
    List<FlagQuestion> findByDeadIsFalseAndQuestionId(String questionId);
    List<FlagQuestion> findByDeadIsFalseAndUserId(String userId);

    FlagQuestion findByDeadIsFalseAndUserIdAndQuestionId(String userId, String questionId);
}
