package com.escoladeti.oldowl.stonix.forum.controller;

import com.escoladeti.oldowl.stonix.forum.model.SuperEntity;
import com.escoladeti.oldowl.stonix.forum.repository.SuperRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Felipe on 09/04/2016.
 */
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public abstract class SuperController<T extends SuperEntity, R extends SuperRepository<T>> {

    protected abstract R getRepository();

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<T>> getAll() {
        return new ResponseEntity<>(getRepository().findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}")
    public ResponseEntity<T> getOne(@PathVariable final String id) {
        return new ResponseEntity<>(getRepository().findOne(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<T> create(@RequestBody final T t) {
        return new ResponseEntity<>(getRepository().save(t), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<T> update(@RequestBody final T t) {
        if (null == t.getId()) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        if (null == getRepository().findOne(t.getId())) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(getRepository().save(t), HttpStatus.ACCEPTED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<T> kill(@PathVariable final String id) {
        if (null == id) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }

        final T t = getRepository().findOne(id);
        if (null == t) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        if (!t.kill())
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        getRepository().save(t);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
