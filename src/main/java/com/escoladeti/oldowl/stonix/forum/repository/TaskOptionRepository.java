package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.TaskOption;

import java.util.List;

/**
 * Created by TI2 on 26/10/2016.
 */
public interface TaskOptionRepository extends SuperRepository<TaskOption> {
    List<TaskOption> findByDeadIsFalseAndTaskId(String taskId);
}
