package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.FlagQuestion;
import com.escoladeti.oldowl.stonix.forum.model.LikedQuestion;
import com.escoladeti.oldowl.stonix.forum.model.User;
import com.escoladeti.oldowl.stonix.forum.repository.FlagQuestionRepository;
import com.escoladeti.oldowl.stonix.forum.repository.LikedQuestionRepository;
import com.escoladeti.oldowl.stonix.forum.repository.QuestionRepository;
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
@RequestMapping(FlagQuestionController.MAPPING)
@CrossOrigin("*")
public class FlagQuestionController extends SuperController<FlagQuestion, FlagQuestionRepository> {
    public static final String MAPPING = "/api/questions/flags";

    @Autowired
    private FlagQuestionRepository repository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    QuestionRepository questionRepository;

    @Override
    public FlagQuestionRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<FlagQuestion> create(@RequestBody final FlagQuestion flagQuestion){
        List<FlagQuestion> flags = repository.findByDeadIsFalseAndQuestionId(flagQuestion.getQuestion().getId());
        int totalPunctuationOnFlag = 0;
        flagQuestion.setUser(userRepository.findOne(flagQuestion.getUser().getId()));
        flagQuestion.setQuestion(questionRepository.findOne(flagQuestion.getQuestion().getId()));
        if(flags.size() > 0) {
            for (FlagQuestion flag : flags) {
                totalPunctuationOnFlag += flag.getUser().getPunctuation();
            }
        }
        totalPunctuationOnFlag = totalPunctuationOnFlag + flagQuestion.getUser().getPunctuation();
        ResponseEntity<FlagQuestion> created = super.create(flagQuestion);
        if(totalPunctuationOnFlag > (4 * flagQuestion.getQuestion().getUser().getPunctuation())) {
            User user = userRepository.findOne(flagQuestion.getQuestion().getUser().getId());
            user.setPunctuation(user.getPunctuation() - 10);
            userRepository.save(user);
            questionRepository.delete(flagQuestion.getQuestion());
        }
        return created;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/question/{id}")
    public ResponseEntity<List<FlagQuestion>> getByQuestion(@PathVariable("id") final String id) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndQuestionId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/user/{id}")
    public ResponseEntity<List<FlagQuestion>> getByUser(@PathVariable("id") final String id) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserId(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/find/flag-user-question")
    public ResponseEntity<FlagQuestion> getLikeByUserAndQuestion(@RequestBody final FlagQuestion flagQuestion) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndUserIdAndQuestionId(
                flagQuestion.getUser().getId(), flagQuestion.getQuestion().getId()
        ), HttpStatus.OK);
    }
}