package com.escoladeti.oldowl.stonix.controller;

import com.escoladeti.oldowl.stonix.model.Player;
import com.escoladeti.oldowl.stonix.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * Created by tdc on 09/04/16.
 */
@Controller
@RequestMapping(PlayerController.MAPPING)
public class PlayerController {

    public static final String MAPPING = "/api/player";

    @Autowired
    private PlayerRepository repository;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Player>> getAll() {
        return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Player> post(@RequestBody final Player player) {
        return new ResponseEntity<>(repository.save(player), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
    public ResponseEntity<Player> delete(@PathVariable("id") final String id) {
        if (id == null || repository.findOne(id) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        repository.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity<Player> put(@RequestBody final Player player) {
        if (player.getId() == null || repository.findOne(player.getId()) == null)
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);

        return new ResponseEntity<>(repository.save(player), HttpStatus.ACCEPTED);
    }
}
