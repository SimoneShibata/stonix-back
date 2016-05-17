package com.escoladeti.oldowl.stonix.model;

import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

/**
 * Created by Felipe on 13/05/2016.
 */
@Entity
public class Evaluation {
    @Id
    private final String id;

    private Date created;

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    public String getId() {
        return id;
    }

    public Evaluation() {
        this.id = UUID.randomUUID().toString();
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Evaluation that = (Evaluation) o;
        return Objects.equal(this.created, that.created);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
