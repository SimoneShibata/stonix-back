package com.escoladeti.oldowl.stonix.forum.model;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

import javax.persistence.Entity;

/**
 * Created by tdc on 01/05/16.
 */
@Entity
public class Question extends BasicForum {

    private String title;
    private Integer views;
    private Integer numberAnswers;
    private Boolean answered;

    public Question() {
        super();
        this.views = 0;
        this.numberAnswers = 0;
        this.answered = false;
    }

    public Integer getNumberAnswers() {
        return numberAnswers;
    }

    public void setNumberAnswers(Integer numberAnswers) {
        this.numberAnswers = numberAnswers;
    }

    public void setAnswered(Boolean answered) {
        this.answered = answered;
    }

    public Boolean getAnswered() {
        return answered;
    }

    public void acceptAnswer() {
        this.answered = true;
    }

    public Integer getViews() {
        return views;
    }

    public void setViews(Integer views) {
        this.views = views;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Question that = (Question) o;
        return Objects.equal(this.title, that.title);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(title, views);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("id", getId())
                .add("title", title)
                .add("views", views)
                .add("created", getCreated())
                .add("dead", getDead())
                .add("status", getStatus())
                .add("last_update", getLastUpdate())
                .add("description", getDescription())
                .toString();
    }
}
