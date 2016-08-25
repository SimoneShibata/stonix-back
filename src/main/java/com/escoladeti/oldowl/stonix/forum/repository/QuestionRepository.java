package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.Question;

import java.util.List;

/**
 * Created by tdc on 09/04/16.
 */
public interface QuestionRepository extends SuperRepository<Question> {
    List<Question> findAllByDeadIsFalseOrderByLastUpdateDesc();
}
