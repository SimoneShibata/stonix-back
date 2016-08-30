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
@RequestMapping(LoginController.MAPPING)
public class LoginController extends SuperController<User, UserRepository> {
    public static final String MAPPING = "/api/";

    @Autowired
    private UserRepository repository;

    @Override
    public UserRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/login")
    public ResponseEntity<User> login(@RequestBody User user) {
        User userCompare = repository.findByEmail(user.getEmail());
        try {
            if (user.getPassword().equals(userCompare.getPassword())) {
                userCompare.setAuthenticated(true);
                return super.update(userCompare);
            } else {
                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/logout")
    public ResponseEntity<User> logout(@RequestBody User user) {
        User userCompare = repository.findOne(user.getId());
        try {
            userCompare.setAuthenticated(false);
            return super.update(userCompare);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
