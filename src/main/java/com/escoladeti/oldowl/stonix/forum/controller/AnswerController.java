package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
import com.escoladeti.oldowl.stonix.forum.repository.AnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(AnswerController.MAPPING)
public class AnswerController extends SuperController<Answer, AnswerRepository> {
    public static final String MAPPING = "/api/answers";

    @Autowired
    private AnswerRepository repository;

    @Override
    public AnswerRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Answer> update(@RequestBody final Answer answer) {
        if (answer.getId() == null || answer.getDescription().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        answer.setLastUpdate(new Date(System.currentTimeMillis()));
        return super.update(answer);
    }
}
