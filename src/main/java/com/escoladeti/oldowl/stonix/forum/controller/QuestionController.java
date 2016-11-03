package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
import com.escoladeti.oldowl.stonix.forum.model.Question;
import com.escoladeti.oldowl.stonix.forum.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.lang.NullPointerException;
import java.util.Date;
import java.util.List;

/**
 * Created by Felipe on 09/04/2016.
 */
@RestController
@RequestMapping(QuestionController.MAPPING)
@CrossOrigin("*")
public class QuestionController extends SuperController<Question, QuestionRepository> {
    public static final String MAPPING = "/api/questions";

    @Autowired
    private QuestionRepository repository;

    @Override
    public QuestionRepository getRepository() {
        return repository;
    }

    @Override
    public ResponseEntity<List<Question>> getAll() {
        return new ResponseEntity<>(repository.findAllByDeadIsFalseOrderByLastUpdateDesc(), HttpStatus.OK);
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Question> update(@RequestBody final Question question) {
        if (question.getId() == null || question.getDescription().equals("") || question.getTitle().equals("")) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        question.setLastUpdate(new Date(System.currentTimeMillis()));
        return super.update(question);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<Question> getOne(@PathVariable final String id) {
        try{
        Question question = repository.findOne(id);
        question.setViews(question.getViews() + 1);
        return new ResponseEntity<>(repository.save(question), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{id}/answers")
    public ResponseEntity<Question> postAnswer(@PathVariable("id") final String id, @RequestBody Answer answer){
        Question question = repository.findOne(id);
        answer.setQuestion(question);
        return super.update(question);
    }
    @Override
    @RequestMapping(method = RequestMethod.DELETE, value ="/{id}")
    public ResponseEntity<Question> kill(@PathVariable("id") final String id){
        Question question = repository.findOne(id);
        try{
            if (question.getAnswered().equals(true)){
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }else {
                return super.kill(id);
            }
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<List<Question>> listQuestionsByUser(@PathVariable("id") final String userId){
        List<Question> questions = repository.findByDeadIsFalseAndUserIdOrderByLastUpdateDesc(userId);
        return new ResponseEntity<>(questions, HttpStatus.OK);
    }

}
