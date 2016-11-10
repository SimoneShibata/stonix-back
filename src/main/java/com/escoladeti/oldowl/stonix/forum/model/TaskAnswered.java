package com.escoladeti.oldowl.stonix.forum.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

/**
 * Created by felipe on 10/11/16.
 */
@Entity
public class TaskAnswered extends SuperEntity {

    @ManyToOne
    private User user;

    @ManyToOne
    private Task task;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TaskAnswered that = (TaskAnswered) o;

        if (user != null ? !user.equals(that.user) : that.user != null) return false;
        return task != null ? task.equals(that.task) : that.task == null;

    }

    @Override
    public int hashCode() {
        int result = user != null ? user.hashCode() : 0;
        result = 31 * result + (task != null ? task.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TaskAnswered{" +
                "user=" + user +
                ", task=" + task +
                '}';
    }
}

