package com.escoladeti.oldowl.stonix.forum.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * Created by Felipe on 30/05/16.
 */
@Entity
public class Comment extends Forum {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment that = (Comment) o;
        return Objects.equal(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDescription(), getCreated());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("created", getCreated())
                .add("description", getDescription())
                .add("status", getStatus())
                .toString();
    }
}
