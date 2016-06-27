package com.escoladeti.oldowl.stonix.forum.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Felipe on 30/05/16.
 */
@Entity
public class CommentQuestion extends Comment {

    @ManyToOne
    @JsonIgnore
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

        CommentQuestion that = (CommentQuestion) o;
        return Objects.equal(this.getId(), that.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getDescription(), getCreated());
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("created", getCreated())
                .add("description", getDescription())
                .add("status", getStatus())
                .toString();
    }
}
