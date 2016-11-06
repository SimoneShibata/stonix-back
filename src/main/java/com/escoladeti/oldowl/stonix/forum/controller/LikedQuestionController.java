package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.LikedQuestion;
import com.escoladeti.oldowl.stonix.forum.repository.LikedQuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(LikedQuestionController.MAPPING)
@CrossOrigin("*")
public class LikedQuestionController extends SuperController<LikedQuestion, LikedQuestionRepository> {
    public static final String MAPPING = "/api/questions/likes";

    @Autowired
    private LikedQuestionRepository repository;

    @Override
    public LikedQuestionRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/question/{id}")
    public ResponseEntity<List<LikedQuestion>> getByQuestion(@PathVariable("id") final String id) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndQuestionId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<List<LikedQuestion>> getByUser(@PathVariable("id") final String id) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find/like-user-question")
    public ResponseEntity<LikedQuestion> getLikeByUserAndQuestion(@RequestBody final LikedQuestion likedQuestion) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserIdAndQuestionId(
                likedQuestion.getUser().getId(), likedQuestion.getQuestion().getId()
        ), HttpStatus.OK);
    }
}