package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.Apple;

import java.util.List;

/**
 * Created by TI2 on 25/10/2016.
 */
public interface AppleRepository extends SuperRepository<Apple> {

    List<Apple> findByDeadIsFalseAndTeacherId(String teacherId);
}
