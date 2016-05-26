package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.enums.Status;
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
        if (null == question.getId() || question.getTitle() == "" || question.getDescription() == "") {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (null == getRepository().findOne(question.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        question.setLastUpdate(new Date(System.currentTimeMillis()));

        return new ResponseEntity<>(getRepository().save(question), HttpStatus.ACCEPTED);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Question> getOne(@PathVariable final String id) {
        Question question = repository.findOne(id);
        question.setViews();
        repository.save(question);
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Question> kill(@PathVariable final String id) {
        if (null == id) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        final Question question = repository.findOne(id);
        if (null == question) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!question.kill())
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        question.setStatus(Status.DELETED);
        repository.save(question);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
