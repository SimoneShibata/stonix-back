package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Question;
import com.escoladeti.oldowl.stonix.forum.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Felipe on 09/04/2016.
 */
@Controller
@RequestMapping(QuestionController.MAPPING)
public class QuestionController extends SuperController<Question, QuestionRepository> {
    public static final String MAPPING = "/questions";

    @Autowired
    private QuestionRepository repository;

    @Override
    public QuestionRepository getRepository() {
        return repository;
    }
}
