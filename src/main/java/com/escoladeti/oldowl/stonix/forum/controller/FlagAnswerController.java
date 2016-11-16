package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.FlagAnswer;
import com.escoladeti.oldowl.stonix.forum.model.User;
import com.escoladeti.oldowl.stonix.forum.repository.AnswerRepository;
import com.escoladeti.oldowl.stonix.forum.repository.FlagAnswerRepository;
import com.escoladeti.oldowl.stonix.forum.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(FlagAnswerController.MAPPING)
@CrossOrigin("*")
public class FlagAnswerController extends SuperController<FlagAnswer, FlagAnswerRepository> {
    public static final String MAPPING = "/api/answers/flags";

    @Autowired
    private FlagAnswerRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    AnswerRepository answerRepository;

    @Override
    public FlagAnswerRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FlagAnswer> create(@RequestBody final FlagAnswer flagAnswer){
        List<FlagAnswer> flags = repository.findByDeadIsFalseAndAnswerId(flagAnswer.getAnswer().getId());
        int totalPunctuationOnFlag = 0;
        flagAnswer.setUser(userRepository.findOne(flagAnswer.getUser().getId()));
        flagAnswer.setAnswer(answerRepository.findOne(flagAnswer.getAnswer().getId()));
        if(flags.size() > 0) {
            for (FlagAnswer flag : flags) {
                totalPunctuationOnFlag += flag.getUser().getPunctuation();
            }
        }
        totalPunctuationOnFlag = totalPunctuationOnFlag + flagAnswer.getUser().getPunctuation();
        ResponseEntity<FlagAnswer> created = super.create(flagAnswer);
        if(totalPunctuationOnFlag > (4 * flagAnswer.getAnswer().getUser().getPunctuation())) {
            User user = userRepository.findOne(flagAnswer.getAnswer().getUser().getId());
            user.setPunctuation(user.getPunctuation() - 20);
            userRepository.save(user);
            answerRepository.delete(flagAnswer.getAnswer());
        }
        return created;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/answer/{id}")
    public ResponseEntity<List<FlagAnswer>> getByAnswer(@PathVariable("id") final String id) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndAnswerId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<List<FlagAnswer>> getByUser(@PathVariable("id") final String id) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find/flag-user-answer")
    public ResponseEntity<FlagAnswer> getLikeByUserAndAnswer(@RequestBody final FlagAnswer flagAnswer) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserIdAndAnswerId(
                flagAnswer.getUser().getId(), flagAnswer.getAnswer().getId()
        ), HttpStatus.OK);
    }
}