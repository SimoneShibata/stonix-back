package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.MappedSuperclass;
import java.util.UUID;

/**
 * Created by theonly on 2016-05-21.
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public class SuperEntity {
    @Id
    private final String id;
    private Boolean dead;

    public SuperEntity() {
        this.id = UUID.randomUUID().toString();
        this.dead = false;
    }

    public String getId() {
        return id;
    }

    public boolean kill() {
        this.dead = true;
        return true;
    }

    public Boolean getDead() {
        return dead;
    }
}
