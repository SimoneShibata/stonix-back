package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.CommentQuestion;
import com.escoladeti.oldowl.stonix.forum.repository.CommentQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Felipe on 09/04/2016.
 */
@RestController
@RequestMapping(CommentQuestionController.MAPPING)
public class CommentQuestionController extends SuperController<CommentQuestion, CommentQuestionRepository> {
    public static final String MAPPING = "/api/comment/questions";

    @Autowired
    private CommentQuestionRepository repository;

    @Override
    public CommentQuestionRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/question/{id}")
    public List<CommentQuestion> getAnswersByQuestion(@PathVariable("id") final String id) {
        return repository.findByQuestionIdAndDeadIsFalse(id);
    }
}
