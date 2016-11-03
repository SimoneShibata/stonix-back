package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 * Created by Felipe on 03/11/2016.
 */
@Entity
public class LikedQuestion extends SuperEntity{

    @ManyToOne
    private User user;

    @ManyToOne
    private Question question;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

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

        LikedQuestion that = (LikedQuestion) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return question != null ? question.equals(that.question) : that.question == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (question != null ? question.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "LikedQuestion{" +
                "user=" + user +
                ", question=" + question +
                '}';
    }
}
