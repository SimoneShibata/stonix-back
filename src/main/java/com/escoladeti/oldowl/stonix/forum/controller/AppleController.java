package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Apple;
import com.escoladeti.oldowl.stonix.forum.repository.AppleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(AppleController.MAPPING)
@CrossOrigin("*")
public class AppleController extends SuperController<Apple, AppleRepository> {
    public static final String MAPPING = "/api/apples";
    @Autowired
    private AppleRepository repository;

    @Override
    public AppleRepository getRepository() {
        return repository;
    }

    @RequestMapping(method = RequestMethod.GET, value="/teacher/{teacherId}")
    public ResponseEntity<List<Apple>> getByTeacher(@PathVariable("teacherId") final String teacherId) {
        return new ResponseEntity<>(repository.findByDeadIsFalseAndTeacherId(teacherId), HttpStatus.OK);
    }
}