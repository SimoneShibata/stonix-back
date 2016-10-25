package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.Task;

import java.util.List;

/**
 * Created by TI2 on 25/10/2016.
 */
public interface TaskRepository extends SuperRepository<Task> {

    List<Task> findByDeadIsFalseAndTaskCategoryId(String taskCategoryId);

}
