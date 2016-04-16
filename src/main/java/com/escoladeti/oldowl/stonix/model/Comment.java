package com.escoladeti.oldowl.stonix.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.UUID;

/**
 * Created by tdc on 15/04/16.
 */
@Entity
public class Comment {
    @Id
    private final String id;
    private String description;

    public Comment() {
        id = UUID.randomUUID().toString();
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final Comment comment = (Comment) o;
        return description != null ? description.equals(comment.description) : comment.description == null;
    }

    @Override
    public int hashCode() {
        return description != null ? description.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
