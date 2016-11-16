package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.FlagAnswer;

import java.util.List;

/**
 * Created by Felipe on 03/11/2016.
 */
public interface FlagAnswerRepository extends SuperRepository<FlagAnswer> {
    List<FlagAnswer> findByDeadIsFalseAndAnswerId(String answerId);
    List<FlagAnswer> findByDeadIsFalseAndUserId(String userId);

    FlagAnswer findByDeadIsFalseAndUserIdAndAnswerId(String userId, String answerId);
}
