package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.LikedAnswer;

import java.util.List;

/**
 * Created by Felipe on 03/11/2016.
 */
public interface LikedAnswerRepository extends SuperRepository<LikedAnswer> {
    List<LikedAnswer> findByDeadIsFalseAndAnswerId(String answer);
    List<LikedAnswer> findByDeadIsFalseAndUserId(String userId);

    LikedAnswer findByDeadIsFalseAndUserIdAndAnswerId(String userId, String answerId);
}
