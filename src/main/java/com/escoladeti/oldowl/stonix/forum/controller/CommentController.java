package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Comment;
import com.escoladeti.oldowl.stonix.forum.model.Question;
import com.escoladeti.oldowl.stonix.forum.repository.CommentRepository;
import com.escoladeti.oldowl.stonix.forum.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * Created by Felipe on 09/04/2016.
 */
@RestController
@RequestMapping(CommentController.MAPPING)
public class CommentController extends SuperController<Comment, CommentRepository> {
    public static final String MAPPING = "/api/comments";

    @Autowired
    private CommentRepository repository;

    @Override
    public CommentRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Comment> update(@RequestBody final Comment comment) {
        if (comment.getId() == null || comment.getDescription().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        return super.update(comment);
    }

}
