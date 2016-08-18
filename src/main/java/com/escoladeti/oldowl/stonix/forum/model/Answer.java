package com.escoladeti.oldowl.stonix.forum.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;
import liquibase.database.Database;

import javax.persistence.*;
import java.util.List;

/**
 * Created by tdc on 01/05/16.
 */
@Entity
public class Answer extends BasicForum {
    @ManyToOne
    private Question question;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Answer that = (Answer) o;
        return Objects.equal(this.getDescription(), that.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDescription(), getId());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("created", getCreated())
                .add("dead", getDead())
                .add("status", getStatus())
                .add("last_update", getLastUpdate())
                .add("description", getDescription())
                .toString();
    }
}
