package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Answer;
import com.escoladeti.oldowl.stonix.forum.model.ClassRoom;
import com.escoladeti.oldowl.stonix.forum.model.Question;
import com.escoladeti.oldowl.stonix.forum.model.User;
import com.escoladeti.oldowl.stonix.forum.repository.AnswerRepository;
import com.escoladeti.oldowl.stonix.forum.repository.ClassRoomRepository;
import com.escoladeti.oldowl.stonix.forum.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Felipe on 28/05/2016.
 */
@RestController
@RequestMapping(ClassRoomController.MAPPING)
@CrossOrigin("*")
public class ClassRoomController extends SuperController<ClassRoom, ClassRoomRepository> {
    public static final String MAPPING = "/api/classroom";

    @Autowired
    private ClassRoomRepository repository;

    @Override
    public ClassRoomRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ClassRoom> create(@RequestBody final ClassRoom classRoom) {
        try {
            return new ResponseEntity<>(getRepository().save(classRoom), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/student/{id}")
    public ResponseEntity<ClassRoom> addStudent(@RequestBody final User student, @PathVariable("id") String id) {
        ClassRoom classRoom = repository.findOne(id);
        classRoom.addStudent(student);
        return update(classRoom);
    }
}
