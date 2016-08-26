package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.User;
import com.escoladeti.oldowl.stonix.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Felipe on 09/04/2016.
 */
@RestController
@RequestMapping(UserController.MAPPING)
public class UserController extends SuperController<User, UserRepository> {
    public static final String MAPPING = "/api/users";

    @Autowired
    private UserRepository repository;

    @Override
    public UserRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<User> update(@RequestBody final User user) {
        try {
            if (user.getId() == null || user.getBirth().equals("") || user.getName().equals("")){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            return super.update(user);
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
