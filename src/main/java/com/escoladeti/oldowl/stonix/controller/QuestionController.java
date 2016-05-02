package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.Question;
import com.escoladeti.oldowl.stonix.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Felipe on 09/04/2016.
 */
@Controller
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(QuestionController.MAPPING)
public class QuestionController {
    public static final String MAPPING = "/api/questions";

    @Autowired
    private QuestionRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Question>> getAll() {
        repository.save(new Question("TITULO : " + new Date().toString(), "DESCRIÇÃO :" + new Date().toString()));
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Question> post(@RequestBody final Question question) {
        return new ResponseEntity<>(repository.save(question), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Question> delete(@PathVariable("id") final String id) {
        if (id == null || repository.findOne(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Question> put(@RequestBody final Question question) {
        if (question.getId() == null || repository.findOne(question.getId()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(repository.save(question), HttpStatus.ACCEPTED);
    }
}
