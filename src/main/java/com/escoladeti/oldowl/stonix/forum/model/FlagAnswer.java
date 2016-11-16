package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by Felipe on 16/11/2016.
 */

@Entity
public class FlagAnswer extends SuperEntity{

    @ManyToOne
    private User user;

    @ManyToOne
    private Answer answer;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Answer getAnswer() {
        return answer;
    }

    public void setAnswer(Answer answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlagAnswer that = (FlagAnswer) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return answer != null ? answer.equals(that.answer) : that.answer == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (answer != null ? answer.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "FlagAnswer{" +
                "user=" + user +
                ", answer=" + answer +
                '}';
    }
}
