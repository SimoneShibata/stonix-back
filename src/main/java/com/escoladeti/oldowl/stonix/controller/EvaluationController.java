package com.escoladeti.oldowl.stonix.controller;


import com.escoladeti.oldowl.stonix.model.Evaluation;
import com.escoladeti.oldowl.stonix.repository.EvaluationRepository;
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
@RequestMapping(EvaluationController.MAPPING)
public class EvaluationController {

    public static final String MAPPING = "/api/evaluation";
    @Autowired
    private EvaluationRepository repository;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Evaluation>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Evaluation> post(@RequestBody final Evaluation evaluation) {
        return new ResponseEntity<>(repository.save(evaluation), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Evaluation> delete(@PathVariable("id") final String id) {
        if (id == null || repository.findOne(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Evaluation> put(@RequestBody final Evaluation evaluation) {
        if (evaluation.getId() == null || repository.findOne(evaluation.getId()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(repository.save(evaluation), HttpStatus.ACCEPTED);
    }
}
