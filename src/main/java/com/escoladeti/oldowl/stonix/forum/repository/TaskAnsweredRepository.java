package com.escoladeti.oldowl.stonix.forum.repository;

import com.escoladeti.oldowl.stonix.forum.model.TaskAnswered;

/**
 * Created by TI2 on 25/10/2016.
 */
public interface TaskAnsweredRepository extends SuperRepository<TaskAnswered> {

    TaskAnswered findByDeadIsFalseAndUserIdAndTaskIdAndTaskOptionId(String userId, String taskId, String taskOptionId);
}
