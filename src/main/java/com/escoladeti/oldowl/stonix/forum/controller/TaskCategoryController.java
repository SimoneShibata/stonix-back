package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.TaskCategory;
import com.escoladeti.oldowl.stonix.forum.repository.TaskCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(TaskCategoryController.MAPPING)
@CrossOrigin("*")
public class TaskCategoryController extends SuperController<TaskCategory, TaskCategoryRepository> {
    public static final String MAPPING = "/api/task-category";

    @Autowired
    private TaskCategoryRepository repository;

    @Override
    public TaskCategoryRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/classroom/{classRoomId}")
    public ResponseEntity<List<TaskCategory>> getByClassRoom(@PathVariable("classRoomId") final String classRoomId) {
        try {
            return new ResponseEntity<>(repository.findByDeadIsFalseAndClassRoomId(classRoomId), HttpStatus.ACCEPTED);
        } catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
