package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
import com.escoladeti.oldowl.stonix.forum.model.CommentAnswer;
import com.escoladeti.oldowl.stonix.forum.repository.AnswerRepository;
import com.escoladeti.oldowl.stonix.forum.repository.CommentAnswerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 30/06/2016.
 */
@RestController
@RequestMapping(CommentAnswerController.MAPPING)
@CrossOrigin("*")
public class CommentAnswerController extends SuperController<CommentAnswer, CommentAnswerRepository> {
    public static final String MAPPING = "/api/comment/answers";

    @Autowired
    private CommentAnswerRepository repository;
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public CommentAnswerRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<CommentAnswer> create(@RequestBody final CommentAnswer commentAnswer) {
        try {
            Answer answer = answerRepository.findOne(commentAnswer.getAnswer().getId());
            answer.setNumberComments(answer.getNumberComments() + 1);
            answerRepository.save(answer);
            return new ResponseEntity<>(getRepository().save(commentAnswer), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/answer/{answerId}")
    public List<CommentAnswer> getCommentByAnswer(@PathVariable("answerId") final String answerId) {
        return repository.findByAnswerIdAndDeadIsFalse(answerId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/count/answer/{answerId}")
    public ResponseEntity<Integer> countCommentsByAnswer(@PathVariable("answerId") final String answerId){
        return new ResponseEntity<>(repository.countByAnswerId(answerId), HttpStatus.OK);
    }
}
