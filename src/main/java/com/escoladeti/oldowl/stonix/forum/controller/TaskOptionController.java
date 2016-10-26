package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.TaskOption;
import com.escoladeti.oldowl.stonix.forum.repository.TaskOptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(TaskOptionController.MAPPING)
@CrossOrigin("*")
public class TaskOptionController extends SuperController<TaskOption, TaskOptionRepository> {
    public static final String MAPPING = "/api/tasks/options";

    @Autowired
    private TaskOptionRepository repository;

    @Override
    public TaskOptionRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "list/{taskId}")
    public ResponseEntity<List<TaskOption>> listByTask(@PathVariable("taskId") final String taskId) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndTaskId(taskId), HttpStatus.OK);
    }
}
