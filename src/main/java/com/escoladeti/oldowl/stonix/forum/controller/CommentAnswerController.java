package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.CommentAnswer;
import com.escoladeti.oldowl.stonix.forum.repository.CommentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Felipe on 30/06/2016.
 */
@RestController
@RequestMapping(CommentAnswerController.MAPPING)
public class CommentAnswerController extends SuperController<CommentAnswer, CommentAnswerRepository> {
    public static final String MAPPING = "/api/comment/answers";

    @Autowired
    private CommentAnswerRepository repository;

    @Override
    public CommentAnswerRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/answer/{id}")
    public List<CommentAnswer> getAnswersByQuestion(@PathVariable("id") final String id) {
        return repository.findByAnswerIdAndDeadIsFalse(id);
    }
}
