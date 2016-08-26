package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
import com.escoladeti.oldowl.stonix.forum.model.CommentAnswer;
import com.escoladeti.oldowl.stonix.forum.model.Question;
import com.escoladeti.oldowl.stonix.forum.repository.AnswerRepository;
import com.escoladeti.oldowl.stonix.forum.repository.QuestionRepository;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(AnswerController.MAPPING)
public class AnswerController extends SuperController<Answer, AnswerRepository> {
    public static final String MAPPING = "/api/answers";

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private AnswerRepository repository;

    @Override
    public AnswerRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Answer> update(@RequestBody final Answer answer) {
        try {
            if (answer.getId() == null || answer.getDescription().equals("")) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            answer.setLastUpdate(new Date(System.currentTimeMillis()));
            return super.update(answer);
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/question/{id}")
    public List<Answer> getAnswersByQuestion(@PathVariable("id") final String id) {
        return repository.findByQuestionIdAndDeadIsFalse(id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "{questionId}/better/{answerId}")
    public ResponseEntity<Answer> betterAnswer(@PathVariable("questionId") final String questionId, @PathVariable("answerId") final String answerId) {
        try {
            Question question = questionRepository.findOne(questionId);
            if (!question.getAnswered()) {
                Answer answer = repository.findOne(answerId);
                answer.acceptAnswer();
                question.acceptAnswer();
                questionRepository.save(question);
                return super.update(answer);
            }else{
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
        } catch (NullPointerException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(method = RequestMethod.GET, value = "/nice/{id}")
    public ResponseEntity<Answer> nice(@PathVariable final String id) {
        try{
            Answer answer = repository.findOne(id);
            answer.setNice(answer.getNice() + 1);
            return new ResponseEntity<>(repository.save(answer), HttpStatus.OK);
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
