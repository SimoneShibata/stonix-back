package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.Flair;

import java.util.List;

/**
 * Created by TI2 on 25/10/2016.
 */
public interface FlairRepository extends SuperRepository<Flair> {

    List<Flair> findByDeadIsFalseAndUserId(String userId);
}
