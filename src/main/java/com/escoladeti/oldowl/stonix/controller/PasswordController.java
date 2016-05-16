package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.Password;
import com.escoladeti.oldowl.stonix.repository.PasswordRepository;
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
@RequestMapping(PasswordController.MAPPING)
public class PasswordController {

    public static final String MAPPING = "/api/password";
    @Autowired
    private PasswordRepository repository;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Password>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Password> post(@RequestBody final Password password) {
        return new ResponseEntity<>(repository.save(password), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Password> delete(@PathVariable("id") final String id) {
        if (id == null || repository.findOne(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Password> put(@RequestBody final Password password) {
        if (password.getId() == null || repository.findOne(password.getId()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(repository.save(password), HttpStatus.ACCEPTED);
    }
}
