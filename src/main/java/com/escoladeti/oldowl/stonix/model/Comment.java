package com.escoladeti.oldowl.stonix.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.UUID;

/**
 * Created by tdc on 15/04/16.
 */
@Entity
public class Comment extends SuperEntity {
    private String description;
    @NotNull
    private Date created;

    public Comment() {
        super();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(final Date created) {
        this.created = created;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Comment comment = (Comment) o;

        if (description != null ? !description.equals(comment.description) : comment.description != null) return false;
        return created != null ? created.equals(comment.created) : comment.created == null;
    }

    @Override
    public int hashCode() {
        int result = description != null ? description.hashCode() : 0;
        result = 31 * result + (created != null ? created.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + getId() + '\'' +
                ", description='" + description + '\'' +
                ", created=" + created +
                '}';
    }
}
