package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.KnowledgeArea;
import com.escoladeti.oldowl.stonix.repository.KnowledgeAreaRepository;
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
@RequestMapping(KnowledgeAreaController.MAPPING)
public class KnowledgeAreaController {

    public static final String MAPPING = "/api/knowledgearea";
    @Autowired
    private KnowledgeAreaRepository repository;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<KnowledgeArea>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<KnowledgeArea> post(@RequestBody final KnowledgeArea knowledgeArea) {
        return new ResponseEntity<>(repository.save(knowledgeArea), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<KnowledgeArea> delete(@PathVariable("id") final String id) {
        if (id == null || repository.findOne(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<KnowledgeArea> put(@RequestBody final KnowledgeArea knowledgeArea) {
        if (knowledgeArea.getId() == null || repository.findOne(knowledgeArea.getId()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(repository.save(knowledgeArea), HttpStatus.ACCEPTED);
    }
}
