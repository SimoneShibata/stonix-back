package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.TaskCategory;

import java.util.List;

/**
 * Created by TI2 on 25/10/2016.
 */
public interface TaskCategoryRepository extends SuperRepository<TaskCategory> {
    List<TaskCategory> findByDeadIsFalseAndClassRoomId(String classRoomId);
}
