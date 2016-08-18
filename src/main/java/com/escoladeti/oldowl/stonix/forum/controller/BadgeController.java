package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.Badge;
import com.escoladeti.oldowl.stonix.forum.repository.BadgeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Felipe on 09/04/2016.
 */
@RestController
@RequestMapping(BadgeController.MAPPING)
public class BadgeController extends SuperController<Badge, BadgeRepository> {
    public static final String MAPPING = "/api/badges";

    @Autowired
    private BadgeRepository repository;

    @Override
    public BadgeRepository getRepository() {
        return repository;
    }

    @Override
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Badge> update(@RequestBody final Badge badge) {
        try {
            if (badge.getId() == null || badge.getDescription().equals("") || badge.getName().equals("") || badge.getImage().equals("")) {
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            }
            return super.update(badge);
        }catch (NullPointerException e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
