package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Apple;
import com.escoladeti.oldowl.stonix.forum.model.ClassRoom;
import com.escoladeti.oldowl.stonix.forum.model.User;
import com.escoladeti.oldowl.stonix.forum.repository.AppleRepository;
import com.escoladeti.oldowl.stonix.forum.repository.ClassRoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}