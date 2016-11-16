package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Felipe on 16/11/2016.
 */
@Entity
public class FlagQuestion extends SuperEntity{

    @ManyToOne
    private Question question;

    @ManyToOne
    private User user;

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlagQuestion that = (FlagQuestion) o;

        if (question != null ? !question.equals(that.question) : that.question != null) return false;
        return user != null ? user.equals(that.user) : that.user == null;

    }

    @Override
    public int hashCode() {
        int result = question != null ? question.hashCode() : 0;
        result = 31 * result + (user != null ? user.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FlagQuestion{" +
                "question=" + question +
                ", user=" + user +
                '}';
    }
}
