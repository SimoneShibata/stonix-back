package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.User;
import com.escoladeti.oldowl.stonix.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

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

    @RequestMapping(method = RequestMethod.GET, value = "/auth")
    public ResponseEntity<User> getAuth(){
        return new ResponseEntity<>(repository.findByAuthenticatedIsTrue(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/assign/xp/{xp}")
    public ResponseEntity<User> assignXp(@RequestBody final User userPost, @PathVariable("xp") final Integer xp){
        try{
            User user = repository.findOne(userPost.getId());
            user.updateXp(xp);
            return super.update(user);
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/assign/punctuation/{points}")
    public ResponseEntity<User> assignPunctuation(@RequestBody final User userPut, @PathVariable("points") final Integer points){
        try{
            User user = repository.findOne(userPut.getId());
            user.updatePunctuation(points);
            return super.update(user);
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ranking/punctuation")
    public ResponseEntity<List<User>> rankingByPunctuation(){
        List<User> users = repository.findAllByDeadIsFalseOrderByPunctuationDesc();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/ranking/level")
    public ResponseEntity<List<User>> rankingByLevel(){
        List<User> users = repository.findAllByDeadIsFalseOrderByLevelDesc();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
