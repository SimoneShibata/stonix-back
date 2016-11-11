package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Flair;
import com.escoladeti.oldowl.stonix.forum.repository.FlairRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(FlairController.MAPPING)
@CrossOrigin("*")
public class FlairController extends SuperController<Flair, FlairRepository> {
    public static final String MAPPING = "/api/flairs";
    @Autowired
    private FlairRepository repository;

    @Override
    public FlairRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value="/user/{userId}")
    public ResponseEntity<List<Flair>> getByTeacher(@PathVariable("userId") final String userId) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserId(userId), HttpStatus.OK);
    }
}