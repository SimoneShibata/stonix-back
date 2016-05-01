package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.Answer;
import com.escoladeti.oldowl.stonix.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by Felipe on 09/04/2016.
 */
@Controller
@RequestMapping(AnswerController.MAPPING)
public class AnswerController {
    public static final String MAPPING = "/api/answers";

    @Autowired
    private AnswerRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Answer>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Answer> post(@RequestBody final Answer answer) {
        return new ResponseEntity<>(repository.save(answer), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Answer> delete(@PathVariable("id") final String id) {
        if (id == null || repository.findOne(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Answer> put(@RequestBody final Answer answer) {
        if (answer.getId() == null || repository.findOne(answer.getId()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(repository.save(answer), HttpStatus.ACCEPTED);
    }
}

