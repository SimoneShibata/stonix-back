package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.LikedAnswer;
import com.escoladeti.oldowl.stonix.forum.model.LikedQuestion;
import com.escoladeti.oldowl.stonix.forum.repository.LikedAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(LikedAnswerController.MAPPING)
@CrossOrigin("*")
public class LikedAnswerController extends SuperController<LikedAnswer, LikedAnswerRepository> {
    public static final String MAPPING = "/api/answers/likes";

    @Autowired
    private LikedAnswerRepository repository;

    @Override
    public LikedAnswerRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/answer/{id}")
    public ResponseEntity<List<LikedAnswer>> getByQuestion(@PathVariable("id") final String id) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndAnswerId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<List<LikedAnswer>> getByUser(@PathVariable("id") final String id) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find/like-user-answer")
    public ResponseEntity<LikedAnswer> getLikeByUserAndQuestion(@RequestBody final LikedAnswer likedAnswer) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserIdAndAnswerId(
                likedAnswer.getUser().getId(), likedAnswer.getAnswer().getId()
        ), HttpStatus.OK);
    }
}