package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.CommentAnswer;
import com.escoladeti.oldowl.stonix.forum.repository.CommentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
