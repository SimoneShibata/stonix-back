package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.Comment;
import com.escoladeti.oldowl.stonix.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by tdc on 15/04/16.
 */
@Controller
@RequestMapping(CommentController.MAPPING)
public class CommentController {
    public static final String MAPPING = "/api/comment";

    @Autowired
    private CommentRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Comment>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Comment> post(@RequestBody final Comment comment) {
        return new ResponseEntity<>(repository.save(comment), HttpStatus.CREATED);
    }
}
