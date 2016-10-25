package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.ClassRoom;
import com.escoladeti.oldowl.stonix.forum.model.Task;
import com.escoladeti.oldowl.stonix.forum.model.User;
import com.escoladeti.oldowl.stonix.forum.repository.ClassRoomRepository;
import com.escoladeti.oldowl.stonix.forum.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(TaskController.MAPPING)
@CrossOrigin("*")
public class TaskController extends SuperController<Task, TaskRepository> {
    public static final String MAPPING = "/api/tasks";

    @Autowired
    private TaskRepository repository;

    @Override
    public TaskRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/task-category/{idTaskCategory}")
    public ResponseEntity<List<Task>> getByCategory(@PathVariable("idTaskCategory") final String idTaskCategory) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndTaskCategoryId(idTaskCategory), HttpStatus.OK);
    }
}
