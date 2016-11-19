package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Apple;
import com.escoladeti.oldowl.stonix.forum.model.TaskAnswered;
import com.escoladeti.oldowl.stonix.forum.repository.AppleRepository;
import com.escoladeti.oldowl.stonix.forum.repository.TaskAnsweredRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(TaskAnsweredController.MAPPING)
@CrossOrigin("*")
public class TaskAnsweredController extends SuperController<TaskAnswered, TaskAnsweredRepository> {
    public static final String MAPPING = "/api/tasks/answered";
    @Autowired
    private TaskAnsweredRepository repository;

    @Override
    public TaskAnsweredRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find")
    public ResponseEntity<TaskAnswered> getByUserIdAndTaskId(@RequestBody final TaskAnswered taskAnswered) {
        String userId = taskAnswered.getUser().getId();
        String taskId = taskAnswered.getTask().getId();
        String taskOptionId = taskAnswered.getTaskOption().getId();

        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserIdAndTaskId(userId, taskId),HttpStatus.OK);
    }
}