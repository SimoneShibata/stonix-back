package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.User;
import com.escoladeti.oldowl.stonix.repository.UserRepository;
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
@RequestMapping(UserController.MAPPING)
public class UserController {

    public static final String MAPPING = "/api/user";
    @Autowired
    private UserRepository repository;
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<User>> getAll(){
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<User> post(@RequestBody final User user) {
        return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<User> delete(@PathVariable("id") final String id) {
        if (id == null || repository.findOne(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> put(@RequestBody final User user) {
        if (user.getId() == null || repository.findOne(user.getId()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(repository.save(user), HttpStatus.ACCEPTED);
    }
}
