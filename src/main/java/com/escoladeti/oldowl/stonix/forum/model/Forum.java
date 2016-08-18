package com.escoladeti.oldowl.stonix.forum.model;

import com.escoladeti.oldowl.stonix.enums.Status;
import com.google.common.base.MoreObjects;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Felipe on 2016-05-21.
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Forum extends SuperEntity {

    private String description;
    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private final Date created;

    @Enumerated(EnumType.STRING)
    private Status status;


    public Forum() {
        this.created = new Date(System.currentTimeMillis());
        this.status = Status.ACTIVE;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("description", description)
                .add("created", created)
                .toString();
    }
}
