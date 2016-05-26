package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Question;
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
@RequestMapping(QuestionController.MAPPING)
public class QuestionController extends SuperController<Question, QuestionRepository> {
    public static final String MAPPING = "/api/questions";

    @Autowired
    private QuestionRepository repository;

    @Override
    public QuestionRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Question> update(@RequestBody final Question question) {
        question.setLastUpdate(new Date(System.currentTimeMillis()));
        return super.update(question);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Question> getOne(@PathVariable final String id) {
        final Question question = repository.findOne(id);
        question.setViews(question.getViews() + 1);
        return new ResponseEntity<>(repository.save(question), HttpStatus.OK);
    }
}